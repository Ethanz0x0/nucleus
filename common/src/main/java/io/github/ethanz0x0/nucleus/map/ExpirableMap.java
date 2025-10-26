package io.github.ethanz0x0.nucleus.map;

import java.util.Map;

/**
 * Expirable map is a map that can automatically remove the data you put in specified time.
 *
 * @see ExpirableHashMap
 * @see ExpirableConcurrentHashMap
 * @param <K>
 *        The type of keys maintained by this map
 * @param <V>
 *        The type of mapped values
*/
public interface ExpirableMap<K, V> extends Map<K, V> {

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
    V put(K key, V value, long expiration);
}
