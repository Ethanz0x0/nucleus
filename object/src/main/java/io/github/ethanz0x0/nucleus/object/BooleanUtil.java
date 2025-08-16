package io.github.ethanz0x0.nucleus.object;

/**
 * A utility class for processing Boolean object.
 */
public class BooleanUtil {

    /**
     * Checks if an object is a boolean.
     *
     * @param object
     *        The object to check
     * @return
     *         Whether the object is a boolean
     * @see
     *      #getBoolean(Object)
     */
    public static boolean isBoolean(Object object) {
        try {
            getBoolean(object);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

   /**
    * Gets boolean from the Object. If the string "true"/"false" is used,
    * the corresponding boolean value is returned.
    * If it is Boolean/boolean itself, it will be forcibly converted to boolean and returned.
    *
    * @param object
    *        The object to parse from
    * @return
    *         The boolean value of the object
    */
    public static boolean getBoolean(Object object) {
        if (ObjectUtil.isObject(object, String.class)) {
            String string = (String) object;
            return "true".equalsIgnoreCase(string);
        }

        if (ObjectUtil.isObject(object, Boolean.class)) {
            return (Boolean) object;
        }

        if (ObjectUtil.isObject(object, boolean.class)) {
            return (boolean) object;
        }

        throw new IllegalArgumentException("Object is not a Boolean/boolean or a string contains boolean");
    }

    private BooleanUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}