package io.github.ethanz0x0.nucleus.object;

/**
 * A utility class for processing characters.
 */
public class CharUtil {

    /**
     * Repeats the specified character and combine them.
     *
     * @param c
     *        The character to be repeated
     * @param amount
     *        The amount to be repeated
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