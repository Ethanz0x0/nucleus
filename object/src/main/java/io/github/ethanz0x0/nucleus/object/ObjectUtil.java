package io.github.ethanz0x0.nucleus.object;

/**
 * A utility class for processing objects.
 */
public class ObjectUtil {

    /**
     * Checks if the object is an instance of a certain type.
     *
     * @param obj
     *        The object to check
     * @param clazz
     *        The type class
     * @return
     *         Whether the object is an instance of the certain type.
     * @param <T>
     *        The generic type
     */
    public static <T> boolean isObject(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj);
    }
    
    private ObjectUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}