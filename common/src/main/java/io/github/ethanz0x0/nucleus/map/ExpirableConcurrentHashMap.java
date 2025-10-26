package io.github.ethanz0x0.nucleus.map;

import java.util.concurrent.*;

import static io.github.ethanz0x0.nucleus.Checks.checkArgument;
import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * Expirable version of concurrent hash map, based on expirable map.
 *
 * @see ExpirableMap
 * @param <K>
 *        The type of keys maintained by this map
 * @param <V>
 *        The type of mapped values
 */
public class ExpirableConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> implements ExpirableMap<K, V> {

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    private final ConcurrentHashMap<K, ScheduledFuture<?>> toRemove = new ConcurrentHashMap<>();

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key
     *        Key with which the specified value is to be associated
     * @param value
     *        Value to be associated with the specified key
     * @param expiration
     *        The time in milliseconds after which the data will be automatically removed
     * @return
     *         The previous value associated with key, or null if there was no mapping for key.
     *         (A null return can also indicate that the map previously associated null with key.)
     */
    @Override
    public V put(K key, V value, long expiration) {
        checkNotNull(key, "key cannot be null");
        checkArgument(expiration > 0, "expiration must be larger than 0");

        if (toRemove.containsKey(key)) {
            toRemove.remove(key).cancel(true);
        }
        V put = super.put(key, value);
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            super.remove(key);
            toRemove.remove(key);
        }, expiration, TimeUnit.MILLISECONDS);
        toRemove.put(key, future);
        return put;
    }

    /**
     * {@inheritDoc}
     *
     * @param key
     *        The key that needs to be removed
     * @return
     *         The previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}
     * @throws NullPointerException
     *         If the specified key is null
     */
    @Override
    public V remove(Object key) {
        checkNotNull(key, "key cannot be null");

        V value = super.remove(key);
        toRemove.remove(key);
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException
     *         If the specified key is null
     */
    @Override
    public boolean remove(Object key, Object value) {
        checkNotNull(key, "key cannot be null");

        boolean success = super.remove(key, value);
        if (success) {
            toRemove.remove(key);
        }
        return success;
    }
}
