package com.github.msarhan.lucene;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * @author Mouaffak A. Sarhan
 */
public final class ArabicNormalizationFilter extends TokenFilter {

    private final ArabicNormalizer normalizer = new ArabicNormalizer();
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);

    public ArabicNormalizationFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            int newlen = normalizer.normalize(termAtt.buffer(), termAtt.length());
            termAtt.setLength(newlen);
            return true;
        }
        return false;
    }
}
