package io.github.ethanz0x0.nucleus;

import java.util.Objects;


/**
 * A simple container to store a pair of related objects, typically used to
 * associate a key with a value.
 *
 * @param <K>
 *        The type of the key object
 * @param <V>
 *        The type of the value object
 */
public class Pair<K, V> {

    /**
     * Creates a instance of the provided key and value.
     *
     * @param k
     *        The key
     * @param v
     *        The value
     * @return
     *         An instance of the provided key and value
     * @param <K>
     *        The type of the key object
     * @param <V>
     *        The type of the value object
     */
    public static <K, V> Pair<K, V> of(K k, V v) {
        return new Pair<>(k, v);
    }

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
