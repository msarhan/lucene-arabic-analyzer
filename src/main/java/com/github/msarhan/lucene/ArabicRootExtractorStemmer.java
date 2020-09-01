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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.lucene.analysis.util.StemmerUtil.delete;

/**
 * Stemmer for Arabic language based on <a target="_blank" href="http://zeus.cs.pacificu.edu/shereen/research.htm#stemming">Khoja's
 * stemmer</a>. <p> Stemming is defined as: <ul> <li> Removal of attached definite article,
 * conjunction, and prepositions. <li> Stemming of common suffixes and prefixes. <li> Extract word
 * root. </ul> <p> Normalization is defined as: <ul> <li> Normalization of hamza with alef seat to a
 * bare alef. <li> Normalization of teh marbuta to heh <li> Normalization of dotless yeh (alef
 * maksura) to yeh. <li> Removal of Arabic diacritics (the harakat) <li> Removal of Kashida
 * (stretching character). </ul>
 *
 * @author Shereen Khoja &lt;s.Khoja@lancaster.ac.uk&gt;
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmer {

    public static final char HAMZA = '\u0621';
    public static final char ALEF_MADDA = '\u0622';
    public static final char ALEF_HAMZA_ABOVE = '\u0623';
    public static final char ALEF_HAMZA_BELOW = '\u0625';
    public static final char ALEF = '\u0627';
    public static final char BEH = '\u0628';
    public static final char TEH_MARBUTA = '\u0629';
    public static final char TEH = '\u062A';
    public static final char REH = '\u0631';
    public static final char ZAI = '\u0632';
    public static final char SEEN = '\u0633';
    public static final char AEN = '\u0639';
    public static final char FEH = '\u0641';
    public static final char KAF = '\u0643';
    public static final char LAM = '\u0644';
    public static final char MEEM = '\u0645';
    public static final char NOON = '\u0646';
    public static final char HEH = '\u0647';
    public static final char WAW = '\u0648';
    public static final char WAW_HAMZA = '\u0624';
    public static final char YEH_MAKSORAH = '\u0649';
    public static final char YEH_HAMZA = '\u0626';
    public static final char YEH = '\u064A';

    public static final char KASHIDA = '\u0640';

    public static final char FATHATAN = '\u064B';
    public static final char DAMMATAN = '\u064C';
    public static final char KASRATAN = '\u064D';
    public static final char FATHA = '\u064E';
    public static final char DAMMA = '\u064F';
    public static final char KASRA = '\u0650';
    public static final char SHADDA = '\u0651';
    public static final char SUKUN = '\u0652';

    public static final char[][] definite_article = {
        new char[]{FEH, ALEF, LAM},
        new char[]{KAF, ALEF, LAM},
        new char[]{BEH, ALEF, LAM},
        new char[]{WAW, ALEF, LAM},
        new char[]{ALEF, LAM}
    };

    public static final char[][] prefixes = {
        new char[]{LAM, LAM},
        new char[]{LAM},
        new char[]{ALEF},
        new char[]{WAW},
        new char[]{SEEN},
        new char[]{BEH},
        new char[]{YEH},
        new char[]{NOON},
        new char[]{MEEM},
        new char[]{TEH},
        new char[]{FEH}
    };

    public static final char[][] suffixes = {
        new char[]{HEH, MEEM, ALEF},
        new char[]{TEH, MEEM, ALEF},
        new char[]{KAF, MEEM, ALEF},
        new char[]{ALEF, NOON},
        new char[]{HEH, ALEF},
        new char[]{WAW, ALEF},
        new char[]{TEH, MEEM},
        new char[]{KAF, MEEM},
        new char[]{TEH, NOON},
        new char[]{KAF, NOON},
        new char[]{NOON, ALEF},
        new char[]{TEH, ALEF},
        new char[]{TEH, ALEF},
        new char[]{WAW, NOON},
        new char[]{YEH, NOON},
        new char[]{HEH, NOON},
        new char[]{HEH, MEEM},
        new char[]{TEH, HEH},
        new char[]{TEH, YEH},
        new char[]{NOON, YEH},
        new char[]{NOON},
        new char[]{KAF},
        new char[]{HEH},
        new char[]{TEH_MARBUTA},
        new char[]{TEH},
        new char[]{ALEF},
        new char[]{YEH},
        new char[]{ALEF, TEH}
    };

    private static List<String> stopwords;
    private static List<String> duplicate;
    private static List<String> first_waw;
    private static List<String> first_yah;
    private static List<String> last_alif;
    private static List<String> last_hamza;
    private static List<String> last_maksoura;
    private static List<String> last_yah;
    private static List<String> mid_waw;
    private static List<String> mid_yah;
    private static List<String> punctuations;
    private static List<String> quad_roots;
    private static List<String> strange;
    private static List<String> tri_patt;
    private static List<String> tri_roots;

    static {
        readInStaticFiles();
    }

    private static void readInStaticFiles() {
        duplicate = fileToWordList("duplicate.txt");
        first_waw = fileToWordList("first_waw.txt");
        first_yah = fileToWordList("first_yah.txt");
        last_alif = fileToWordList("last_alif.txt");
        last_hamza = fileToWordList("last_hamza.txt");
        last_maksoura = fileToWordList("last_maksoura.txt");
        last_yah = fileToWordList("last_yah.txt");
        mid_waw = fileToWordList("mid_waw.txt");
        mid_yah = fileToWordList("mid_yah.txt");
        punctuations = fileToWordList("punctuation.txt");
        quad_roots = fileToWordList("quad_roots.txt");
        stopwords = fileToWordList("stopwords.txt");
        tri_patt = fileToWordList("tri_patt.txt");
        tri_roots = fileToWordList("tri_roots.txt");
        strange = fileToWordList("strange.txt");
    }

    private static List<String> fileToWordList(String fileName) {
        final List<String> words = new ArrayList<>();

        InputStream input = ArabicRootExtractorStemmer.class.getClassLoader()
            .getResourceAsStream("com/github/msarhan/lucene/" + fileName);
        if (input != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            reader.lines()
                .map(line -> line.trim().split("\\s+"))
                .map(Arrays::stream)
                .forEach(stringStream -> stringStream.forEach(words::add));
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return words;
    }

    public String stem(String input) {
        Flags flags = new Flags();

        String output = normalize(input);
        output = removePunctuation(output);
        output = removeNonLetter(output);

        if (!checkStrangeWords(output)) {
            if (!checkStopwords(output, flags)) {
                output = additionalStem(output, flags);
            }
        }

        return output;
    }

    private String additionalStem(String input, Flags flags) {

        // check if the word consists of two letters
        // and find it's root
        if (input.length() == 2) {
            input = processTwoLetters(input, flags);
        }

        // if the word consists of three letters
        if (input.length() == 3 && !flags.rootFound)
        // check if it's a root
        {
            input = processThreeLetters(input, flags);
        }

        // if the word consists of four letters
        if (input.length() == 4)
        // check if it's a root
        {
            processFourLetters(input, flags);
        }

        // if the root hasn't yet been found
        if (!flags.rootFound) {
            // check if the word is a pattern
            input = checkPatterns(input, flags);
        }

        // if the root still hasn't been found
        if (!flags.rootFound) {
            // check for a definite article, and remove it
            input = removeDefiniteArticle(input, flags);
        }

        // if the root still hasn't been found
        if (!flags.rootFound && !flags.stopwordFound) {
            // check for the prefix waw
            input = checkPrefixWaw(input, flags);
        }

        // if the root STILL hasnt' been found
        if (!flags.rootFound && !flags.stopwordFound) {
            // check for suffixes
            input = checkForSuffixes(input, flags);
        }

        // if the root STILL hasn't been found
        if (!flags.rootFound && !flags.stopwordFound) {
            // check for prefixes
            input = checkForPrefixes(input, flags);
        }

        return input;
    }

    private String checkForPrefixes(String input, Flags flags) {
        String output = input;

        // for every prefix in the list
        for (char[] prefix : prefixes) {
            // if the prefix was found
            if (startsWith(output.toCharArray(), prefix)) {
                output = output.substring(prefix.length);

                // check to see if the word is a stopword
                if (checkStopwords(output, flags)) {
                    return output;
                }

                // check to see if the word is a root of three or four letters
                // if the word has only two letters, test to see if one was removed
                if (output.length() == 2) {
                    output = processTwoLetters(output, flags);
                } else if (output.length() == 3 && !flags.rootFound) {
                    output = processThreeLetters(output, flags);
                } else if (output.length() == 4) {
                    processFourLetters(output, flags);
                }

                // if the root hasn't been found, check for patterns
                if (!flags.rootFound && output.length() > 2) {
                    output = checkPatterns(output, flags);
                }

                // if the root STILL hasn't been found
                if (!flags.rootFound && !flags.stopwordFound && !flags.fromSuffixes) {
                    // check for suffixes
                    output = checkForSuffixes(output, flags);
                }

                if (flags.stopwordFound) {
                    return output;
                }

                // if the root was found, return the modified word
                if (flags.rootFound && !flags.stopwordFound) {
                    return output;
                }
            }
        }

        return input;
    }

    private String checkForSuffixes(String input, Flags flags) {
        String output = input;
        flags.fromSuffixes = true;

        // for every suffix in the list
        for (char[] suffix : suffixes) {
            // if the suffix was found
            if (endsWith(output.toCharArray(), suffix)) {
                output = output.substring(0, output.length() - suffix.length);

                // check to see if the word is a stopword
                if (checkStopwords(output, flags)) {
                    flags.fromSuffixes = false;
                    return output;
                }

                // check to see if the word is a root of three or four letters
                // if the word has only two letters, test to see if one was removed
                if (output.length() == 2) {
                    output = processTwoLetters(output, flags);
                } else if (output.length() == 3) {
                    output = processThreeLetters(output, flags);
                } else if (output.length() == 4) {
                    processFourLetters(output, flags);
                }

                // if the root hasn't been found, check for patterns
                if (!flags.rootFound && output.length() > 2) {
                    output = checkPatterns(output, flags);
                }

                if (flags.stopwordFound) {
                    flags.fromSuffixes = false;
                    return output;
                }

                // if the root was found, return the modified word
                if (flags.rootFound) {
                    flags.fromSuffixes = false;
                    return output;
                }
            }
        }
        flags.fromSuffixes = false;

        return input;
    }

    private String checkPrefixWaw(String input, Flags flags) {
        String output;

        if (input.length() > 3 && input.charAt(0) == WAW) {
            output = input.substring(1);

            // check to see if the word is a stopword
            if (checkStopwords(output, flags)) {
                return output;
            }

            // check to see if the word is a root of three or four letters
            // if the word has only two letters, test to see if one was removed
            if (output.length() == 2) {
                output = processTwoLetters(output, flags);
            } else if (output.length() == 3 && !flags.rootFound) {
                output = processThreeLetters(output, flags);
            } else if (output.length() == 4) {
                processFourLetters(output, flags);
            }

            // if the root hasn't been found, check for patterns
            if (!flags.rootFound && output.length() > 2) {
                output = checkPatterns(output, flags);
            }

            // if the root STILL hasnt' been found
            if (!flags.rootFound && !flags.stopwordFound) {
                // check for suffixes
                output = checkForSuffixes(output, flags);
            }

            // iIf the root STILL hasn't been found
            if (!flags.rootFound && !flags.stopwordFound) {
                // check for prefixes
                output = checkForPrefixes(output, flags);
            }

            if (flags.stopwordFound) {
                return output;
            }

            if (flags.rootFound && !flags.stopwordFound) {
                return output;
            }
        }

        return input;
    }

    private String removeDefiniteArticle(String input, Flags flags) {
        // looking through the vector of definite articles
        // search through each definite article, and try and
        // find a match
        String output = "";

        // for every definite article in the list
        for (char[] definiteArticle : definite_article) {
            // if the definite article was found
            if (startsWith(input.toCharArray(), definiteArticle)) {
                // remove the definite article
                output = input.substring(definiteArticle.length);

                // check to see if the word is a stopword
                if (checkStopwords(output, flags)) {
                    return output;
                }

                // check to see if the word is a root of three or four letters
                // if the word has only two letters, test to see if one was removed
                if (output.length() == 2) {
                    output = processTwoLetters(output, flags);
                } else if (output.length() == 3 && !flags.rootFound) {
                    output = processThreeLetters(output, flags);
                } else if (output.length() == 4) {
                    processFourLetters(output, flags);
                }

                // if the root hasn't been found, check for patterns
                if (!flags.rootFound && output.length() > 2) {
                    output = checkPatterns(output, flags);
                }

                // if the root STILL hasnt' been found
                if (!flags.rootFound && !flags.stopwordFound) {
                    // check for suffixes
                    output = checkForSuffixes(output, flags);
                }

                // if the root STILL hasn't been found
                if (!flags.rootFound && !flags.stopwordFound) {
                    // check for prefixes
                    output = checkForPrefixes(output, flags);
                }

                if (flags.stopwordFound) {
                    return output;
                }

                // if the root was found, return the modified word
                if (flags.rootFound && !flags.stopwordFound) {
                    return output;
                }
            }
        }

        if (output.length() > 3) {
            return output;
        }

        return input;
    }

    private String processTwoLetters(String input, Flags flags) {
        // if the input consists of two letters, then this could be either
        // - because it is a root consisting of two letters (though I can't think of any!)
        // - because a letter was deleted as it is duplicated or a weak middle or last letter.

        input = processDuplicate(input, flags);

        // check if the last letter was weak
        if (!flags.rootFound) {
            input = lastWeak(input, flags);
        }

        // check if the first letter was weak
        if (!flags.rootFound) {
            input = firstWeak(input, flags);
        }

        // check if the middle letter was weak
        if (!flags.rootFound) {
            input = middleWeak(input, flags);
        }

        return input;
    }

    private String processThreeLetters(String input, Flags flags) {
        StringBuilder output = new StringBuilder(input);
        String root = "";
        // if the first letter is a 'Ç', 'Ä'  or 'Æ'
        // then change it to a 'Ã'
        if (input.length() > 0) {
            if (input.charAt(0) == ALEF || input.charAt(0) == WAW_HAMZA
                || input.charAt(0) == YEH_HAMZA) {
                output.setLength(0);
                output.append(ALEF_HAMZA_ABOVE);
                output.append(input.substring(1));
                root = output.toString();
            }

            // if the last letter is a weak letter or a hamza
            // then remove it and check for last weak letters
            if (input.charAt(2) == WAW || input.charAt(2) == YEH || input.charAt(2) == ALEF ||
                input.charAt(2) == YEH_MAKSORAH || input.charAt(2) == HAMZA
                || input.charAt(2) == YEH_HAMZA) {
                root = input.substring(0, 2);
                root = lastWeak(root, flags);
                if (flags.rootFound) {
                    return root;
                }
            }

            // if the second letter is a weak letter or a hamza
            // then remove it
            if (input.charAt(1) == WAW || input.charAt(1) == YEH || input.charAt(1) == ALEF
                || input.charAt(1) == YEH_HAMZA) {
                root = input.substring(0, 1);
                root = root + input.substring(2);

                root = middleWeak(root, flags);
                if (flags.rootFound) {
                    return root;
                }
            }

            // if the second letter has a hamza, and it's not on a alif
            // then it must be returned to the alif
            if (input.charAt(1) == WAW_HAMZA || input.charAt(1) == YEH_HAMZA) {
                if (input.charAt(2) == MEEM || input.charAt(2) == ZAI || input.charAt(2) == REH) {
                    root = input.substring(0, 1);
                    root = root + ALEF;
                    root = root + input.substring(2);
                } else {
                    root = input.substring(0, 1);
                    root = root + ALEF_HAMZA_ABOVE;
                    root = root + input.substring(2);
                }
            }

            // if the last letter is a shadda, remove it and
            // duplicate the last letter
            if (input.charAt(2) == SHADDA) {
                root = input.substring(0, 1);
                root = root + input.substring(1, 2);
            }
        }

        // if word is a root, then flags.rootFound is true
        if (root.length() == 0) {
            if (tri_roots.contains(input)) {
                flags.rootFound = true;
                return input;
            }
        }
        // check for the root that we just derived
        else if (tri_roots.contains(root)) {
            flags.rootFound = true;
            return root;
        }

        return input;
    }

    // if the input has four letters
    private void processFourLetters(String input, Flags flags) {
        // if word is a root, then flags.rootFound is true
        if (quad_roots.contains(input)) {
            flags.rootFound = true;
        }
    }

    private String checkPatterns(String input, Flags flags) {
        StringBuilder root = new StringBuilder();
        // if the first letter is a hamza, change it to an alif
        if (input.length() > 0) {
            if (input.charAt(0) == ALEF_HAMZA_ABOVE || input.charAt(0) == ALEF_HAMZA_BELOW
                || input.charAt(0) == ALEF_MADDA) {
                root.append("j");
                root.setCharAt(0, ALEF);
                root.append(input.substring(1));
                input = root.toString();
            }
        }

        // try and find a pattern that matches the word
        int numberSameLetters;
        String pattern;
        String output;

        // for every pattern
        for (String aTri_patt : tri_patt) {
            pattern = aTri_patt;
            root.setLength(0);
            // if the length of the words are the same
            if (pattern.length() == input.length()) {
                numberSameLetters = 0;
                // find out how many letters are the same at the same index
                // so long as they're not a fa, ain, or lam
                for (int j = 0; j < input.length(); j++) {
                    if (pattern.charAt(j) == input.charAt(j) &&
                        pattern.charAt(j) != FEH &&
                        pattern.charAt(j) != AEN &&
                        pattern.charAt(j) != LAM) {
                        numberSameLetters++;
                    }
                }

                // test to see if the word matches the pattern ÇÝÚáÇ
                if (input.length() == 6 && input.charAt(3) == input.charAt(5)
                    && numberSameLetters == 2) {
                    root.append(input.charAt(1));
                    root.append(input.charAt(2));
                    root.append(input.charAt(3));
                    output = root.toString();
                    output = processThreeLetters(output, flags);
                    if (flags.rootFound) {
                        return output;
                    } else {
                        root.setLength(0);
                    }
                }

                // if the word matches the pattern, get the root
                if (input.length() - 3 <= numberSameLetters) {
                    // derive the root from the word by matching it with the pattern
                    for (int j = 0; j < input.length(); j++) {
                        if (pattern.charAt(j) == FEH ||
                            pattern.charAt(j) == AEN ||
                            pattern.charAt(j) == LAM) {
                            root.append(input.charAt(j));
                        }
                    }

                    output = root.toString();
                    output = processThreeLetters(output, flags);

                    if (flags.rootFound) {
                        input = output;
                        return input;
                    }
                }
            }
        }

        return input;
    }

    private String removeNonLetter(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (Character.isLetter(input.charAt(i))) {
                output.append(input.charAt(i));
            }
        }

        return output.toString();
    }

    private String processDuplicate(String input, Flags flags) {
        // check if a letter was duplicated
        if (duplicate.contains(input)) {
            // if so, then return the deleted duplicate letter
            input = input + input.substring(1);

            // root was found, so set variable
            flags.rootFound = true;
        }

        return input;
    }

    private String lastWeak(String input, Flags flags) {
        // check if the last letter was an alif
        if (last_alif.contains(input)) {
            flags.rootFound = true;
            return input + ALEF;
        }
        // check if the last letter was an hamza
        else if (last_hamza.contains(input)) {
            flags.rootFound = true;
            return input + ALEF_HAMZA_ABOVE;
        }
        // check if the last letter was an maksoura
        else if (last_maksoura.contains(input)) {
            flags.rootFound = true;
            return input + YEH_MAKSORAH;
        }
        // check if the last letter was an yah
        else if (last_yah.contains(input)) {
            flags.rootFound = true;
            return input + YEH;
        }

        return input;
    }

    private String firstWeak(String input, Flags flags) {
        // check if the first letter was a waw
        if (first_waw.contains(input)) {
            flags.rootFound = true;
            return WAW + input;
        }
        // check if the first letter was a yah
        else if (first_yah.contains(input)) {
            flags.rootFound = true;
            return YEH + input;
        }

        return input;
    }

    private String middleWeak(String input, Flags flags) {
        // check if the middle letter is a waw
        if (mid_waw.contains(input)) {
            flags.rootFound = true;
            // return the waw to the word
            return input.charAt(0) + (WAW + input.substring(1));
        }
        // check if the middle letter is a yah
        else if (mid_yah.contains(input)) {
            flags.rootFound = true;
            // return the waw to the word
            return input.charAt(0) + (YEH + input.substring(1));
        }

        return input;
    }

    private String removePunctuation(String input) {
        StringBuilder output = new StringBuilder();

        // for every character in the current word, if it is a punctuation then do nothing
        // otherwise, copy this character to the modified word
        for (int i = 0; i < input.length(); i++) {
            if (!(punctuations.contains(input.substring(i, i + 1)))) {
                output.append(input.charAt(i));
            }
        }

        return output.toString();
    }

    private boolean checkStopwords(String input, Flags flags) {
        return (flags.stopwordFound = stopwords.contains(input));
    }

    private boolean checkStrangeWords(String input) {
        return strange.contains(input);
    }

    /**
     * Normalize an input string of Arabic text
     *
     * @param input input string
     * @return input string after normalization
     */
    public String normalize(String input) {
        char[] s = input.toCharArray();
        int len = s.length;

        for (int i = 0; i < len; i++) {
            switch (s[i]) {
                case KASHIDA:
                case KASRATAN:
                case DAMMATAN:
                case FATHATAN:
                case FATHA:
                case DAMMA:
                case KASRA:
                case SUKUN:
                    len = delete(s, i, len);
                    i--;
                    break;
                default:
                    break;
            }
        }

        return new String(s, 0, len);
    }

    /**
     * Returns true if the prefix matches
     *
     * @param input  input buffer
     * @param prefix prefix to check
     * @return true if the prefix matches
     */
    boolean startsWith(char[] input, char[] prefix) {
        if (input.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (input[i] != prefix[i]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the suffix matches
     *
     * @param input  input buffer
     * @param suffix suffix to check
     * @return true if the suffix matches
     */
    boolean endsWith(char[] input, char[] suffix) {
        if (input.length < suffix.length) {
            return false;
        }

        for (int i = 0; i < suffix.length; i++) {
            if (input[input.length - suffix.length + i] != suffix[i]) {
                return false;
            }
        }

        return true;
    }

    private static class Flags {

        boolean rootFound = false;
        boolean stopwordFound = false;
        boolean fromSuffixes = false;
    }

}
