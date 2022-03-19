[![Build Status](https://travis-ci.com/msarhan/lucene-arabic-analyzer.svg?branch=master)](https://travis-ci.com/msarhan/lucene-arabic-analyzer)
[![Javadoc](https://www.javadoc.io/badge/com.github.msarhan/lucene-arabic-analyzer.svg)](https://www.javadoc.io/doc/com.github.msarhan/lucene-arabic-analyzer)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/msarhan/lucene-arabic-analyzer/blob/master/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.msarhan/lucene-arabic-analyzer.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.msarhan/lucene-arabic-analyzer)
[![Apache Lucene](https://img.shields.io/badge/Apache%20Lucene-8.2.x-green)](https://lucene.apache.org/core/8_8_2/index.html)

# lucene-arabic-analyzer
Apache Lucene analyzer for Arabic language with root based stemmer.

Stemming algorithms are used in information retrieval systems, text classifiers, indexers and text mining to extract roots of different words, so that words derived from the same stem or root are grouped together.
- Version `2.x` is based on [Alkhlil Morpho System](https://ossl.alecso.org/affich_oso_details.php).
- Version `1.x` is based on [Khoja stemmer](http://zeus.cs.pacificu.edu/shereen/research.htm#stemming).

`ArabicRootExtractorAnalyzer` is responsible to do the following:

1. Normalize input text by removing diacritics: e.g. "الْعَالَمِينَ" will be converted to "العالمين".
2. Extract word's root: e.g. "العالمين" will be converted to "علم".

This way, documents will be indexed depending on its words roots, so, when you want to search in the index, you can input "علم" or "عالم" to get all documents containing "الْعَالَمِينَ".

## Installation

**Maven**
```xml
<dependency>
  <groupId>com.github.msarhan</groupId>
  <artifactId>lucene-arabic-analyzer</artifactId>
  <version>[VERSION]</version>
</dependency>
```

## Usage

```java
//Initialize the index
Directory index = new RAMDirectory();
Analyzer analyzer = new ArabicRootExtractorAnalyzer();
IndexWriterConfig config = new IndexWriterConfig(analyzer);
IndexWriter writer = new IndexWriter(index, config);

Document doc = new Document();
doc.add(new StringField("number", "1", Field.Store.YES));
doc.add(new TextField("title", "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ", Field.Store.YES));
writer.addDocument(doc);

doc = new Document();
doc.add(new StringField("number", "2", Field.Store.YES));
doc.add(new TextField("title", "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ", Field.Store.YES));
writer.addDocument(doc);

doc = new Document();
doc.add(new StringField("number", "3", Field.Store.YES));
doc.add(new TextField("title", "الرَّحْمَنِ الرَّحِيمِ", Field.Store.YES));
writer.addDocument(doc);
writer.close();
//~

//Query the index
String queryStr = "راحم";
Query query = new QueryParser("title", analyzer)
    .parse(queryStr);

int hitsPerPage = 5;
IndexReader reader = DirectoryReader.open(index);
IndexSearcher searcher = new IndexSearcher(reader);
TopDocs docs = searcher.search(query, hitsPerPage, Sort.INDEXORDER);

ScoreDoc[] hits = docs.scoreDocs;
//~

//Print results
System.out.println("Found " + hits.length + " hits:");
for (ScoreDoc hit : hits) {
    int docId = hit.doc;
    Document d = searcher.doc(docId);
    System.out.printf("\t(%s): %s\n", d.get("number"), d.get("title"));
}
//~
```

### Usage of `ArabicRootExtractorStemmer`
```java
ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();

assertTrue(stemmer.stem("الرَّحْمَنِ").stream().anyMatch(s -> s.equals("رحم")));
assertTrue(stemmer.stem("الْعَالَمِينَ").stream().anyMatch(s -> s.equals("علم")));
assertTrue(stemmer.stem("الْمُؤْمِنِينَ").stream().anyMatch(s -> s.equals("ءمن")));
assertTrue(stemmer.stem("يَتَنَازَعُونَ").stream().anyMatch(s -> s.equals("نزع")));
```

## Building
```bash
# Install AlKhalil jar files in your local maven repository
cd alkhalil && ./maven-install.sh

# The resulting jar file will include Alkhalil dependencies
mvn package
```
