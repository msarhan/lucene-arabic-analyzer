package com.github.msarhan.lucene;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.ar.ArabicNormalizationFilter;
import org.apache.lucene.analysis.ar.ArabicStemFilter;
import org.apache.lucene.analysis.core.DecimalDigitFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mouaffak A. Sarhan
 */
public class StandardArabicAnalyzerTest {

    @Test
    public void stem() throws Exception {
        List<String> res = analyze("إِنَّهُمْ إِنْ يَظْهَرُوا عَلَيْكُمْ يَرْجُمُوكُمْ أَوْ يُعِيدُوكُمْ فِي مِلَّتِهِمْ وَلَنْ تُفْلِحُوا إِذًا أَبَدًا");
        System.out.println(res);
    }

    private static List<String> analyze(String text) throws IOException {
        List<String> result = new ArrayList<>();
        TokenStream tokenStream = new CustomArabicAnalyzer(
            new CharArraySet(
                Arrays.asList("إذا", "في", "على"),
                false
            )
        ).tokenStream("title", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        return result;
    }

    private static class CustomArabicAnalyzer extends StopwordAnalyzerBase {

        protected CustomArabicAnalyzer(CharArraySet stopwords) {
            super(stopwords);
        }

        @Override
        protected TokenStreamComponents createComponents(String fieldName) {
            final Tokenizer source = new StandardTokenizer();
            TokenStream result = new LowerCaseFilter(source);
            result = new DecimalDigitFilter(result);
            result = new StopFilter(result, stopwords);
            result = new ArabicNormalizationFilter(result);
            result = new ArabicStemFilter(result);
            return new TokenStreamComponents(source, result);
        }
    }

}
