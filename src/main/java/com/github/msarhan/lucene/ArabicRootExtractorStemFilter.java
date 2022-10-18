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

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.CharsRefBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A {@link TokenFilter} that applies {@link ArabicRootExtractorStemmer}. <p> To prevent terms from
 * being stemmed use an instance of {@link SetKeywordMarkerFilter} or a custom {@link TokenFilter}
 * that sets the {@link KeywordAttribute} before this {@link TokenStream}. </p>
 *
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 * @see SetKeywordMarkerFilter
 */
public final class ArabicRootExtractorStemFilter extends TokenFilter {

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);
    private final KeywordAttribute keywordAttr = addAttribute(KeywordAttribute.class);
    private final ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
    private List<CharsRef> buffer;
    private State savedState;

    public ArabicRootExtractorStemFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (buffer != null && !buffer.isEmpty()) {
            CharsRef nextStem = buffer.remove(0);
            restoreState(savedState);
            posIncAtt.setPositionIncrement(0);
            termAtt.setEmpty().append(nextStem);
            return true;
        }

        if (!input.incrementToken()) {
            return false;
        }

        if (keywordAttr.isKeyword()) {
            return true;
        }

        buffer = this.stemmer.stem(termAtt.toString())
            .stream()
            .map(root -> new CharsRefBuilder().append(root).get())
            .collect(Collectors.toList());

        if (buffer.isEmpty()) { // we do not know this word, return it unchanged
            return true;
        }

        CharsRef stem = buffer.remove(0);
        termAtt.setEmpty().append(stem);

        if (!buffer.isEmpty()) {
            savedState = captureState();
        }

        return true;
    }
}
