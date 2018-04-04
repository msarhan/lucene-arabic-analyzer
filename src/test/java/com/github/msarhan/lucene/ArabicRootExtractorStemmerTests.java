package com.github.msarhan.lucene;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmerTests {

    @Test
    public void testStemmer() {
        ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();

        assertEquals("رحم", stemmer.stem("الرَّحْمَنِ"));
        assertEquals("رحم", stemmer.stem("الرَّحِيمِ"));
        assertEquals("علم", stemmer.stem("الْعَالَمِينَ"));
        assertEquals("عبد", stemmer.stem("نَعْبُدُ"));
        assertEquals("صرط", stemmer.stem("صِرَاطَ"));
        assertEquals("نعم", stemmer.stem("أَنْعَمْتَ"));
        assertEquals("أمن", stemmer.stem("الْمُؤْمِنِينَ"));
        assertEquals("صلح", stemmer.stem("الصَّالِحَاتِ"));
        assertEquals("حدث", stemmer.stem("الْحَدِيثِ"));
        assertEquals("سمي", stemmer.stem("السَّمَاوَاتِ"));
        assertEquals("سلط", stemmer.stem("بِسُلْطَانٍ"));
        assertEquals("فلح", stemmer.stem("تُفْلِحُوا"));
        assertEquals("نزع", stemmer.stem("يَتَنَازَعُونَ"));
        assertEquals("شرب", stemmer.stem("الشَّرَابُ"));
        assertEquals("غفل", stemmer.stem("أَغْفَلْنَا"));
        assertEquals("خلل", stemmer.stem("خِلالَهُمَا"));
        assertEquals("قلب", stemmer.stem("مُنقَلَبًا"));
        assertEquals("خوي", stemmer.stem("خَاوِيَةٌ"));
        assertEquals("بقي", stemmer.stem("وَالْبَاقِيَاتُ"));
        assertEquals("جرم", stemmer.stem("الْمُجْرِمِينَ"));
        assertEquals("ظلم", stemmer.stem("لِلظَّالِمِينَ"));
        //assertEquals("أخذ", stemmer.stem("لَنَتَّخِذَنَّ"));
    }

}
