package io.github.ethanz0x0.nucleus.object;

import java.text.DecimalFormat;

/**
 * A utility class for processing numbers.
 */
public class NumberUtil {

    /**
     * Converts an integer to its Roman numeral representation.
     *
     * @param num
     *        The integer to be converted
     * @return
     *        The Roman numeral string
     */
    public static String intToRoman(int num) {
        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4,
                1
        };
        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV",
                "I"
        };

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }

    /**
     * Converts a double into an unitized string with suffixes
     * (k, M, B, T).
     *
     * @param number
     *        The number to be unitized
     * @return
     *        The unitized string representation
     */
    public static String unitize(double number) {
        String[] suffixes = {"", "k", "M", "B", "T"};

        int suffixIndex = 0;
        while (number >= 1000 && suffixIndex < suffixes.length - 1) {
            number /= 1000;
            suffixIndex++;
        }

        return String.format("%.2f%s", number, suffixes[suffixIndex]);
    }

    /**
     * Formats a double as a string using a specified pattern.
     *
     * @param number
     *        The number to be formatted
     * @param pattern
     *        The decimal format pattern
     * @return
     *        The formatted string
     */
    public static String format(double number, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }

    /**
     * Formats a double as a string in the format
     * {@code 1,234,567.89}.
     *
     * @param number
     *        The number to be formatted
     * @return
     *        The formatted string
     * @see #format(double, String)
     */
    public static String formatCommon(double number) {
        return format(number, "#,###.##");
    }

    /**
     * Checks if a string can be parsed as a number of the given type.
     *
     * @param obj
     *        The string to be checked
     * @param clazz
     *        The number class
     * @return
     *        Whether the string is a valid number
     * @param <T>
     *        The number type
     * @see #getNumber(String, Class)
     */
    public static <T extends Number> boolean isNumber(String obj, Class<T> clazz) {
        try {
            getNumber(obj, clazz);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parses a string into a number of the given type.
     *
     * @param obj
     *        The string to be parsed
     * @param clazz
     *        The number class
     * @return
     *        The parsed number
     * @param <T>
     *        The number type
     */
    public static <T extends Number> T getNumber(String obj, Class<T> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return clazz.cast(Integer.parseInt(obj));
        } else if (clazz == Double.class || clazz == double.class) {
            return clazz.cast(Double.parseDouble(obj));
        } else if (clazz == Long.class || clazz == long.class) {
            return clazz.cast(Long.parseLong(obj));
        } else if (clazz == Short.class || short.class == clazz) {
            return clazz.cast(Short.parseShort(obj));
        } else if (clazz == Float.class || float.class == clazz) {
            return clazz.cast(Float.parseFloat(obj));
        } else if (clazz == Byte.class || byte.class == clazz) {
            return clazz.cast(Byte.parseByte(obj));
        } else {
            throw new IllegalArgumentException("Unsupported number class " + clazz.getName());
        }
    }

    /**
     * Gets a {@link Number} instance from an object.
     *
     * @param obj
     *        The object to be converted
     * @return
     *        The number instance
     * @throws IllegalArgumentException
     *         If the object is not a type of {@link Number}
     */
    public static Number getObject(Object obj) {
        if (!ObjectUtil.isObject(obj, Number.class)) {
            throw new IllegalArgumentException("Object is not a type Number");
        }
        return (Number) obj;
    }

    private NumberUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
