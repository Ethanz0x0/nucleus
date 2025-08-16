package io.github.ethanz0x0.nucleus.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for processing lists.
 */
public class ListUtil {

    /**
     * Finds an element from a list by reflecting over the fields
     * in the list and matching them against the expected values.
     *
     * @param list
     *        The list to search in
     * @param field
     *        The field
     * @param expectedValue
     *        The expected value
     * @return
     *         A list of all matched elements
     * @param <T>
     *        The type of the element
     */
    public static <T> List<T> getElementByFieldValue(List<T> list, String field, Object expectedValue) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        List<T> elements = new ArrayList<>();
        for (T obj : list) {
            Field f;
            try {
                f = obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            f.setAccessible(true);
            try {
                if (f.get(obj).equals(expectedValue)) {
                    elements.add(obj);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return elements;
    }

    /**
     * Checks if it is a list of a specified generic type.
     *
     * @param list
     *        The list to be checked
     * @param clazz
     *        The generic type
     * @return
     *         Whether the list is of a specified generic type
     * @param <T>
     *        The generic type
     */
    public static <T> boolean isList(List<Object> list, Class<T> clazz) {
        if (list.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return ObjectUtil.isObject(list.get(0), clazz);
    }

    /**
     * Checks if a list is null or empty.
     *
     * @param input
     *        The list to be checked
     * @return
     *         Whether if a list is null or empty
     */
    public static boolean isNullOrEmpty(List<String> input) {
        return input == null || input.isEmpty();
    }

    private ListUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}