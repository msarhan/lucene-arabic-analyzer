package com.github.msarhan.lucene;

import AlKhalil2.util.cache.AlKhalilDataCache;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mouaffak A. Sarhan &lt;mouffaksarhan@gmail.com&gt;
 */
public class ArabicRootExtractorStemmerTests {

    @Test
    public void stem() {
        ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
        System.out.println(stemmer.stem("أبدا"));

        final ArabicNormalizer normalizer = new ArabicNormalizer();
        char[] text = "أَبَدًا".toCharArray();
        int len = normalizer.normalize(text, text.length);
        System.out.println(new String(text, 0, len));

        text = "أبدا".toCharArray();
        len = normalizer.normalize(text, text.length);
        System.out.println(new String(text, 0, len));

        assertTrue(stemmer.stem("الرَّحْمَنِ").stream().anyMatch(s -> s.equals("رحم")));
        assertTrue(stemmer.stem("الْعَالَمِينَ").stream().anyMatch(s -> s.equals("علم")));
        assertTrue(stemmer.stem("الْمُؤْمِنِينَ").stream().anyMatch(s -> s.equals("ءمن")));
        assertTrue(stemmer.stem("يَتَنَازَعُونَ").stream().anyMatch(s -> s.equals("نزع")));
    }

    @Test
    public void cacheIsSharedAcrossThreads() throws Exception {
        List<String> words = List.of("الرَّحْمَنِ", "الْعَالَمِينَ", "الْمُؤْمِنِينَ", "يَتَنَازَعُونَ");
        int threads = 4;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        try {
            List<Future<List<Set<String>>>> futures = IntStream.range(0, threads)
                .mapToObj(i -> pool.submit(() -> {
                    ArabicRootExtractorStemmer stemmer = new ArabicRootExtractorStemmer();
                    return words.stream().map(stemmer::stem).collect(Collectors.toList());
                }))
                .toList();

            List<Set<String>> reference = futures.get(0).get(60, TimeUnit.SECONDS);
            for (int i = 1; i < threads; i++) {
                assertEquals(reference, futures.get(i).get(60, TimeUnit.SECONDS));
            }
        } finally {
            pool.shutdown();
        }

        assertTrue(AlKhalilDataCache.size() > 0, "shared XML cache should be populated");
    }
}
