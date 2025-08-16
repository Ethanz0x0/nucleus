package io.github.ethanz0x0.nucleus.object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A utility class for processing maps.
 */
public class MapUtil {

    /**
     * Uses the value to query all entries that meet the conditions in the map.
     *
     * @param map
     *        The map to be reversed in
     * @param value
     *        The value to be reversed
     * @return
     *         The found map entry
     * @param <K>
     *        The type of the key object
     * @param <V>
     *        The type of the value object
     */
    public static <K, V> Map.Entry<K, V>[] reverseEntriesByValue(Map<K, V> map, V value) {
        List<Map.Entry<K, V>> entries = new ArrayList<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                entries.add(entry);
            }
        }

        @SuppressWarnings("unchecked")
        Map.Entry<K, V>[] array = (Map.Entry<K, V>[]) Array.newInstance(Map.Entry.class, entries.size());
        return entries.toArray(array);
    }
    
    private MapUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}