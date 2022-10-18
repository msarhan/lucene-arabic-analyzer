package com.github.msarhan.lucene;

import org.junit.jupiter.api.Test;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmerTests {

    @Test
    public void stem() {
        ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
        //System.out.println(stemmer.stem("أَبَدًا"));
        System.out.println(stemmer.stem("أبدا"));

        final ArabicNormalizer normalizer = new ArabicNormalizer();
        char[] text = "أَبَدًا".toCharArray();
        int len = normalizer.normalize(text, text.length);
        System.out.println(new String(text, 0, len));

        text = "أبدا".toCharArray();
        len = normalizer.normalize(text, text.length);
        System.out.println(new String(text, 0, len));

        /*assertTrue(stemmer.stem("الرَّحْمَنِ").stream().anyMatch(s -> s.equals("رحم")));
        assertTrue(stemmer.stem("الْعَالَمِينَ").stream().anyMatch(s -> s.equals("علم")));
        assertTrue(stemmer.stem("الْمُؤْمِنِينَ").stream().anyMatch(s -> s.equals("ءمن")));
        assertTrue(stemmer.stem("يَتَنَازَعُونَ").stream().anyMatch(s -> s.equals("نزع")));*/
    }
}
