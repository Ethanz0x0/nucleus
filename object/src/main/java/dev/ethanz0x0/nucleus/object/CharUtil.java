package dev.ethanz0x0.nucleus.object;

/**
 * A utility class for processing characters.
 */
public class CharUtil {

    /**
     * Repeats the specified character and combine them.
     *
     * @return
     *         A string containing repeated chars
     * @see
     *      StringUtil#repeat(String, int)
     */
    public static String repeat(char c, int amount) {
        return StringUtil.repeat(String.valueOf(c), amount);
    }

    private CharUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}