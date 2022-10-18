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

import AlKhalil2.morphology.analyzer.AnalyzerTokens;
import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.util.Settings;

import java.util.*;

/**
 * Factory for {@link ArabicRootExtractorStemFilter}.
 *
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmer {

    public Set<String> stem(String token) {
        final AnalyzerTokens analyzerTokens = new AnalyzerTokens();
        Set<String> roots = new LinkedHashSet<>(1);
        for (Object o : analyzerTokens.analyzerToken(token)) {
            Result res = (Result) o;
            roots.add(!"#".equals(res.getRoot()) ? res.getRoot() : res.getStem());
        }
        return roots;
    }

    static {
        Settings.changeSettings(
            false,
            false,
            true, // stem
            false,
            false,
            false,
            false,
            false,
            true, // root
            false,
            false
        );
    }
}
