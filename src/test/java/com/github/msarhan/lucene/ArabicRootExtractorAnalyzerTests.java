/*
 * The MIT License
 *
 * Copyright 2015 Mouaffak A. Sarhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.msarhan.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorAnalyzerTests {

    @Test
    public void testArabicRootIndex(@TempDir Path tempDir) throws IOException, ParseException, URISyntaxException {
        Directory index = new MMapDirectory(tempDir);
        ArabicRootExtractorAnalyzer analyzer = new ArabicRootExtractorAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        final AtomicInteger id = new AtomicInteger(0);
        IndexWriter w = new IndexWriter(index, config);
        URL url = ArabicRootExtractorStemmer.class.getClassLoader()
            .getResource("com/github/msarhan/lucene/fateha.txt");

        if (url == null) {
            Assertions.fail("Not able to load data file!");
        }

        Files.lines(new File(url.toURI()).toPath())
            .forEach(line -> addDoc(w, line, String.valueOf(id.incrementAndGet())));
        w.close();

        String querystr = "راحم";
        Query q = new QueryParser("title", analyzer)
            .parse(querystr);

        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);

        //print(searcher, docs);

        Assertions.assertEquals(2, docs.scoreDocs.length);
    }

    private void addDoc(IndexWriter w, String title, String number) {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("number", number, Field.Store.YES));
        try {
            w.addDocument(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print(IndexSearcher searcher, TopDocs docs) throws IOException {
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.println("Found " + hits.length + " hits.");
        for (ScoreDoc hit : hits) {
            int docId = hit.doc;
            Document d = searcher.doc(docId);
            System.out.println(d.get("number") + "\t" + d.get("title"));
        }
    }

    @Test
    public void testInlineStemmer() throws IOException, ParseException {
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

        /*
        System.out.println("Found " + hits.length + " hits:");
		for (ScoreDoc hit : hits) {
			int docId = hit.doc;
			Document d = searcher.doc(docId);
			System.out.printf("\t(%s): %s\n", d.get("number"), d.get("title"));
		}
		*/
    }

}
