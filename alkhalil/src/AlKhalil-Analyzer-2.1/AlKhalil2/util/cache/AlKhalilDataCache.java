package AlKhalil2.util.cache;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * Process-wide cache for AlKhalil's parsed XML data. Each entry corresponds to one XML
 * resource (or one logical slice of one) and is parsed at most once for the life of the JVM.
 *
 * <p>Loaders are serialized through a single lock during warmup. This is intentional: many of
 * the loaders rely on AlKhalil's mutable {@code Database} statics for resource-path resolution,
 * which would otherwise race across concurrent loads. After warmup, reads hit the fast path
 * with no locking.
 *
 * <p>Returned collections are wrapped in unmodifiable views so accidental mutation (notably the
 * inherited {@code clear()} methods on factory base classes) fails fast instead of silently
 * corrupting the shared cache.
 */
public final class AlKhalilDataCache {

    private static final ConcurrentMap<String, Object> CACHE = new ConcurrentHashMap<String, Object>();
    private static final Object LOCK = new Object();

    private AlKhalilDataCache() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T compute(String key, Supplier<T> loader) {
        Object cached = CACHE.get(key);
        if (cached != null) {
            return (T) cached;
        }
        synchronized (LOCK) {
            cached = CACHE.get(key);
            if (cached != null) {
                return (T) cached;
            }
            Object value = freeze(loader.get());
            CACHE.put(key, value);
            return (T) value;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Object freeze(Object value) {
        if (value instanceof Map) {
            return Collections.unmodifiableMap((Map) value);
        }
        if (value instanceof List) {
            return Collections.unmodifiableList((List) value);
        }
        return value;
    }

    public static int size() {
        return CACHE.size();
    }

    public static void resetForTests() {
        CACHE.clear();
    }
}
