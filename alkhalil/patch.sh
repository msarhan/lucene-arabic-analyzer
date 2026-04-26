#!/bin/zsh
set -e
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
cp AlKhalil-Analyzer-origin-2.1.jar AlKhalil-Analyzer-2.1.jar
cd src/AlKhalil-Analyzer-2.1

# Patched + new sources for the runtime XML cache (Phase 4 of refactor).
# AnalyzerDerivedFactory was the original lazy-load patch and stays.
PATCHED_SOURCES=(
  AlKhalil2/util/cache/AlKhalilDataCache.java
  AlKhalil2/morphology/derived/analyzer/factory/AnalyzerDerivedFactory.java
  AlKhalil2/stemmer/clitic/impl/Enclitic.java
  AlKhalil2/stemmer/clitic/impl/Proclitic.java
  AlKhalil2/morphology/exceptional/Exceptional.java
  AlKhalil2/morphology/derived/formulas/impl/FormulasDerivedNominal.java
  AlKhalil2/morphology/derived/formulas/impl/FormulasDerivedVerbal.java
  AlKhalil2/morphology/derived/postag/impl/POSTagDerivedNominal.java
  AlKhalil2/morphology/derived/postag/impl/POSTagDerivedVerbal.java
  AlKhalil2/morphology/derived/root/impl/RootDerivedNominal.java
  AlKhalil2/morphology/derived/root/impl/RootDerivedVerbal.java
  AlKhalil2/morphology/derived/type/impl/TypeDerivedNominal.java
  AlKhalil2/morphology/derived/type/impl/TypeDerivedVerbal.java
  AlKhalil2/morphology/derived/unvoweled/impl/UnvoweledDerivedNominal.java
  AlKhalil2/morphology/derived/unvoweled/impl/UnvoweledDerivedVerbal.java
  AlKhalil2/morphology/derived/voweled/impl/VoweledDerivedNominal.java
  AlKhalil2/morphology/derived/voweled/impl/VoweledDerivedVerbal.java
  AlKhalil2/morphology/underived/type/impl/TypeUnderivedToolwords.java
  AlKhalil2/morphology/underived/type/impl/TypeUnderivedPropernoun.java
  AlKhalil2/morphology/underived/unvoweled/impl/UnvoweledUnderivedToolwords.java
  AlKhalil2/morphology/underived/unvoweled/impl/UnvoweledUnderivedPropernoun.java
  AlKhalil2/morphology/underived/voweled/impl/VoweledUnderivedToolwords.java
  AlKhalil2/morphology/underived/voweled/impl/VoweledUnderivedPropernoun.java
)

javac -cp ../../AlKhalil-Analyzer-2.1.jar "${PATCHED_SOURCES[@]}"

PATCHED_CLASSES=("${PATCHED_SOURCES[@]/%.java/.class}")
jar uf ../../AlKhalil-Analyzer-2.1.jar "${PATCHED_CLASSES[@]}"

cd -
./maven-install.sh
