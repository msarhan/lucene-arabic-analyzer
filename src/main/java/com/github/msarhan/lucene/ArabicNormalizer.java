package com.github.msarhan.lucene;

import static org.apache.lucene.analysis.util.StemmerUtil.delete;

/**
 * @author Mouaffak A. Sarhan
 */
public final class ArabicNormalizer {

    public static final char TATWEEL = '\u0640';

    public static final char FATHATAN = '\u064B';
    public static final char DAMMATAN = '\u064C';
    public static final char KASRATAN = '\u064D';
    public static final char FATHA = '\u064E';
    public static final char DAMMA = '\u064F';
    public static final char KASRA = '\u0650';
    public static final char SHADDA = '\u0651';
    public static final char SUKUN = '\u0652';

    public int normalize(char s[], int len) {
        for (int i = 0; i < len; i++) {
            switch (s[i]) {
                case TATWEEL:
                case KASRATAN:
                case DAMMATAN:
                case FATHATAN:
                case FATHA:
                case DAMMA:
                case KASRA:
                case SHADDA:
                case SUKUN:
                    len = delete(s, i, len);
                    i--;
                    break;
                default:
                    break;
            }
        }

        return len;
    }

}
