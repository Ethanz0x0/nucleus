package dev.ethanz0x0.nucleus.object;

/**
 * A utility class for processing objects.
 */
public class ObjectUtil {

    /**
     * Checks if the object is an instance of a certain type.
     *
     * @return
     *         Whether the object is an instance of the certain type.
     */
    public static <T> boolean isObject(Object obj, Class<T> clazz) {
        return clazz.isInstance(obj);
    }
    
    private ObjectUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}