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
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.DecimalDigitFilter;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.standard.std40.StandardTokenizer40;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Analyzer} for Arabic language.
 * <p>
 * This analyzer implements root-based stemmer to extract triliteral Arabic roots as specified by:
 * <i>
 * <a target="_blank" href="http://zeus.cs.pacificu.edu/shereen/research.htm#stemming">Khoja's stemmer</a>
 * </i>
 * <p>
 * The analysis package contains two primary components:
 * <ul>
 * <li>{@link ArabicRootExtractorStemFilter}: Arabic orthographic normalization and root extraction.
 * <li>Arabic stop words file: a set of default Arabic stop words.
 * </ul>
 *
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public final class ArabicRootExtractorAnalyzer extends StopwordAnalyzerBase {

    /**
     * File containing default Arabic stopwords.
     * <p>
     * Default stopword list is from http://members.unine.ch/jacques.savoy/clef/index.html
     * The stopword list is BSD-Licensed.
     */
    public final static String DEFAULT_STOPWORD_FILE = "stopwords.txt";

    /**
     * Returns an unmodifiable instance of the default stop-words set.
     *
     * @return an unmodifiable instance of the default stop-words set.
     */
    public static CharArraySet getDefaultStopSet() {
        return DefaultSetHolder.DEFAULT_STOP_SET;
    }

    /**
     * Atomically loads the DEFAULT_STOP_SET in a lazy fashion once the outer class
     * accesses the static final set the first time.;
     */
    private static class DefaultSetHolder {
        static final CharArraySet DEFAULT_STOP_SET;

        static {
            try {
                DEFAULT_STOP_SET = loadStopwordSet(false, ArabicRootExtractorAnalyzer.class, DEFAULT_STOPWORD_FILE, "#");
            } catch (IOException ex) {
                // default set should always be present as it is part of the
                // distribution (JAR)
                throw new RuntimeException("Unable to load default stopword set");
            }
        }
    }

    private final CharArraySet stemExclusionSet;

    /**
     * Builds an analyzer with the default stop words: {@link #DEFAULT_STOPWORD_FILE}.
     */
    public ArabicRootExtractorAnalyzer() {
        this(DefaultSetHolder.DEFAULT_STOP_SET);
    }

    /**
     * Builds an analyzer with the given stop words
     *
     * @param stopwords a stopword set
     */
    public ArabicRootExtractorAnalyzer(CharArraySet stopwords) {
        this(stopwords, CharArraySet.EMPTY_SET);
    }

    /**
     * Builds an analyzer with the given stop word. If a none-empty stem exclusion set is
     * provided this analyzer will add a {@link SetKeywordMarkerFilter} before
     * {@link ArabicRootExtractorStemmer}.
     *
     * @param stopwords        a stopword set
     * @param stemExclusionSet a set of terms not to be stemmed
     */
    public ArabicRootExtractorAnalyzer(CharArraySet stopwords, CharArraySet stemExclusionSet) {
        super(stopwords);
        this.stemExclusionSet = CharArraySet.unmodifiableSet(CharArraySet.copy(stemExclusionSet));
    }

    /**
     * Creates
     * {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     * used to tokenize all the text in the provided {@link Reader}.
     *
     * @return {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     * built from an {@link StandardTokenizer} filtered with
     * {@link LowerCaseFilter}, {@link DecimalDigitFilter}, {@link StopFilter},
     * {@link ArabicRootExtractorStemFilter}, {@link SetKeywordMarkerFilter}
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source;
        if (getVersion().onOrAfter(Version.LATEST)) {
            source = new StandardTokenizer();
        } else {
            source = new StandardTokenizer40();
        }
        TokenStream result = new LowerCaseFilter(source);
        if (getVersion().onOrAfter(Version.LUCENE_5_4_0)) {
            result = new DecimalDigitFilter(result);
        }
        // the order here is important: the stopword list is not normalized!
        result = new StopFilter(result, stopwords);
        result = new ArabicRootExtractorStemFilter(result);
        if (!stemExclusionSet.isEmpty()) {
            result = new SetKeywordMarkerFilter(result, stemExclusionSet);
        }

        return new TokenStreamComponents(source, result);
    }
}

