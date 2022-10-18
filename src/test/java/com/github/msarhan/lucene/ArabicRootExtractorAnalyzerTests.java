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
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorAnalyzerTests {

    @Test
    public void searchUsingVerb() throws Exception {
        TopFieldDocs result = search("كتب");
        Assertions.assertEquals(1, result.totalHits.value);
        result = search("قال");
        Assertions.assertEquals(2, result.totalHits.value);
    }

    @Test
    public void searchUsingNoun() throws Exception {
        TopFieldDocs result = search("صالح");
        Assertions.assertEquals(1, result.totalHits.value);

        result = search("أب");
        Assertions.assertEquals(1, result.totalHits.value);
    }

    @Test
    public void analyze() throws IOException {
        Assertions.assertIterableEquals(
            Arrays.asList("رحم", "رحم"),
            analyze("الرَّحْمَنِ الرَّحِيمِ")
        );

        Assertions.assertIterableEquals(
            Arrays.asList("مكث", "بدو", "ءبد", "بدد"),
            analyze("مَاكِثِينَ فِيهِ أَبَدًا")
        );
    }

    private @TempDir Path tempDir;
    private Directory index;
    private IndexSearcher searcher;
    private QueryParser parser;

    private TopFieldDocs search(String query) throws Exception {
        return this.searcher.search(this.parser.parse(query), 100, Sort.INDEXORDER);
    }

    private static List<String> analyze(String text) throws IOException {
        List<String> result = new ArrayList<>();
        TokenStream tokenStream = new ArabicRootExtractorAnalyzer().tokenStream("title", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        return result;
    }

    @BeforeEach
    void init() throws Exception {
        index = new MMapDirectory(tempDir);
        Analyzer analyzer = new ArabicRootExtractorAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        final List<String> input = Arrays.asList(
            "الْحَمْدُ لِلَّهِ الَّذِي أَنْزَلَ عَلَى عَبْدِهِ الْكِتَابَ وَلَمْ يَجْعَلْ لَهُ عِوَجًا",
            "قَيِّمًا لِيُنْذِرَ بَأْسًا شَدِيدًا مِنْ لَدُنْهُ وَيُبَشِّرَ الْمُؤْمِنِينَ الَّذِينَ يَعْمَلُونَ الصَّالِحَاتِ أَنَّ لَهُمْ أَجْرًا حَسَنًا",
            "مَاكِثِينَ فِيهِ أَبَدًا",
            "وَيُنْذِرَ الَّذِينَ قَالُوا اتَّخَذَ اللَّهُ وَلَدًا",
            "مَا لَهُمْ بِهِ مِنْ عِلْمٍ وَلَا لِآبَائِهِمْ كَبُرَتْ كَلِمَةً تَخْرُجُ مِنْ أَفْوَاهِهِمْ إِنْ يَقُولُونَ إِلَّا كَذِبًا"
        );

        for (String text : input) {
            Document doc = new Document();
            doc.add(new StringField("number", String.valueOf(System.currentTimeMillis()), Field.Store.YES));
            doc.add(new TextField("text", text, Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();

        this.parser = new QueryParser("text", new ArabicRootExtractorAnalyzer());
        IndexReader reader = DirectoryReader.open(index);
        this.searcher = new IndexSearcher(reader);
    }

    @AfterEach
    void clean() throws IOException {
        this.index.close();
    }
}
