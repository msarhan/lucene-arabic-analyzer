# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test

The project depends on the AlKhalil 2.1 morphology jars under `alkhalil/`, which are not on Maven Central. They must be installed into the local Maven repo before any build:

```bash
cd alkhalil && ./maven-install.sh   # one-time, installs alkhalil-analyzer / db-long / db-short 2.1
mvn package                          # builds shaded jar (includes AlKhalil)
mvn test                             # runs JUnit 5 tests
mvn -Dtest=ArabicRootExtractorStemmerTests test          # single test class
mvn -Dtest=ArabicRootExtractorStemmerTests#methodName test
```

Toolchain: JDK 11 (`maven.compiler.source/target=11`), Lucene 9.3.0, JUnit Jupiter 5.9.0. Lucene deps are `provided` scope — consumers supply Lucene; AlKhalil is bundled via `maven-shade-plugin`.

The `sign` profile (`mvn -Psign deploy`) is for releasing to OSSRH and requires GPG keys.

## Architecture

The library is a thin Lucene-9 wrapper over the **AlKhalil 2 Arabic morphological analyzer**. The pipeline assembled in `ArabicRootExtractorAnalyzer#createComponents` is:

`StandardTokenizer → LowerCaseFilter → DecimalDigitFilter → ArabicNormalizationFilter → StopFilter → [SetKeywordMarkerFilter] → ArabicRootExtractorStemFilter`

Key seams:

- **`ArabicNormalizationFilter` / `ArabicNormalizer`** — strips Arabic diacritics (tashkeel) and normalizes letter forms before stemming. Runs before stop-word filtering so stop words match either form.
- **`ArabicRootExtractorStemFilter` → `ArabicRootExtractorStemmer`** — the Lucene `TokenFilter` delegates to `ArabicRootExtractorStemmer.stem(token)`, which calls AlKhalil's `AnalyzerTokens.analyzerToken(...)`. AlKhalil returns multiple morphological analyses; the stemmer collects each `Result.getRoot()` (falling back to `getStem()` when root is `"#"`) into a `LinkedHashSet`. **Multiple roots per token are emitted as synonym-style tokens at the same position** (see commit `42f5a87`); changes here affect index/query token positions.
- **AlKhalil `Settings`** — configured once in a `static {}` block in `ArabicRootExtractorStemmer` to enable only `stem` and `root` outputs. AlKhalil's API uses global mutable settings, so do not toggle these per-call.
- **`ArabicRootExtractorStemFilterFactory`** — SPI factory so the filter is usable from Solr/Elasticsearch-style XML/JSON analyzer configs.
- **`StandardArabicAnalyzerTest`** — exists to compare behavior against Lucene's built-in `ArabicAnalyzer`; keep it as a regression baseline when changing the pipeline.

The companion Elasticsearch plugin lives in a separate repo (`msarhan/elasticsearch-arabic-analyzer`) and consumes the shaded jar produced here.

## AlKhalil jars & sources

`alkhalil/` contains the upstream `AlKhalil-*-2.1.jar` files plus helper scripts (`decompile.sh`, `replace.sh`, `original/`) used to patch the upstream binaries. `maven-install.sh` is the source of truth for the local Maven coordinates referenced in `pom.xml` (`alkhalil:alkhalil-analyzer`, `alkhalil:alkhalil-db-long`, `alkhalil:alkhalil-db-short`, all `2.1`). If those coordinates change, update `pom.xml` and `dependency-reduced-pom.xml` together.

AlKhalil-Analyzer-2.1.jar sources are decompiled under `alkhalil/src/AlKhalil-Analyzer-2.1`. `replace.sh` recompiles a curated list of patched `.java` files against the original jar and `jar uf`'s the resulting `.class` files back in.

### Patched files & shared XML cache

Upstream AlKhalil factory `impl` classes SAX-parse 180+ XML resources (~20–30 MB) lazily into per-instance `Map`/`List` fields. To avoid re-parsing per thread / per stemmer, the patched jar adds:

- **`AlKhalil2/util/cache/AlKhalilDataCache.java`** — process-wide `ConcurrentHashMap<String, Object>` keyed by a stable identifier (e.g. `"Enclitic"`, `"DerivedNTRootC:ا"`, `"DerivedNFormulas:5"`). `compute(key, supplier)` does double-checked load under a single global `LOCK` so that AlKhalil's `Database.set*()` static-mutable path resolution is serialized during warmup. Cached values are wrapped in `Collections.unmodifiableMap` / `unmodifiableList` to fail-fast on accidental mutation — **do not re-introduce `clear()` calls in patched factories**; they expect per-instance mutability that no longer exists.
- **Patched factory impls** under `morphology/{derived,underived,exceptional}/**/impl/` and `stemmer/clitic/impl/{Enclitic,Proclitic}.java` — each `add*()` method delegates to `AlKhalilDataCache.compute(...)` instead of constructing a fresh parser. Explicit constructors were added because the decompiler dropped field initializers; verify with `javap` if a future patch breaks.
- **`ArabicRootExtractorStemmer`** keeps a static `ThreadLocal<AnalyzerTokens>` for the lightweight mutable per-thread state (Stemming buffers, exception analyzers); the heavy parsed-XML tables are now globally shared via the cache.

If upstream AlKhalil ever ships a new factory `impl` that calls `openFileXml(...)` directly, route it through `AlKhalilDataCache` and add the source path to the `PATCHED_SOURCES` array in `alkhalil/replace.sh`, otherwise that factory will revert to per-thread XML parsing.
