package com.github.msarhan.lucene;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmerTests {

    @Test
    public void testStemmer() {
        ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();

        assertTrue(stemmer.stem("الرَّحْمَنِ").stream().anyMatch(s -> s.equals("رحم")));
        assertTrue(stemmer.stem("الْعَالَمِينَ").stream().anyMatch(s -> s.equals("علم")));
        assertTrue(stemmer.stem("الْمُؤْمِنِينَ").stream().anyMatch(s -> s.equals("ءمن")));
        assertTrue(stemmer.stem("يَتَنَازَعُونَ").stream().anyMatch(s -> s.equals("نزع")));
    }

    @Test
    public void token() {
        ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
        System.out.println(stemmer.stem("الْمُؤْمِنِينَ"));
    }

}
