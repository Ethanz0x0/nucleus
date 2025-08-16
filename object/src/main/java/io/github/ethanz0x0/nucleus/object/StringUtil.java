package io.github.ethanz0x0.nucleus.object;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for processing strings.
 */
public class StringUtil {

    /**
     * Checks if a string is empty or all filled with spaces.
     *
     * @param string
     *        The string to be checked
     * @return
     *         Whether if a string is empty or all filled with spaces
     */
    public static boolean isBlank(String string) {
        return string.trim().isEmpty();
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param input
     *        The string to be checked
     * @return
     *         Whether if a string is null or empty
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    /**
     * Checks if a string is null or all filled with spaces.
     *
     * @param input
     *        The string to be checked
     * @return
     *         Whether if a string is null or all filled with spaces
     * @see
     *      #isBlank(String)
     */
    public static boolean isNullOrBlank(String input) {
        return isNullOrEmpty(input) || isBlank(input);
    }

    /**
     * Case ignoring version of {@link String#contains(CharSequence)}.
     *
     * @param string
     *        The string to be checked
     * @param pattern
     *        The substring.
     * @return
     *         Whether if a string contains another substring, case-insensitive
     */
    public static boolean containsIgnoreCase(String string, String pattern) {
        if (string == null || pattern == null) {
            return false;
        }

        int patternLength = pattern.length();
        int strLength = string.length();
        int maxIndex = strLength - patternLength;

        for (int i = 0; i <= maxIndex; i++) {
            if (string.regionMatches(true, i, pattern, 0, patternLength)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Case ignoring version of {@link String#replace(CharSequence, CharSequence)}.
     *
     * @param string
     *        The string to be checked
     * @param pattern
     *        The substring to be replaced
     * @param replacement
     *        The replacement
     * @return
     *         The replaced string
     */
    public static String replaceIgnoreCase(String string, String pattern, String replacement) {
        Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(string);
        return matcher.replaceAll(replacement);
    }

    /**
     * Calculates similarity between two strings.
     *
     * @param s1
     *        The string to be checked
     * @param s2
     *        Another string
     * @return
     *         The similarity between two strings
     */
    public static double calculateSimilarity(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) {
            set1.add(c);
        }

        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);
        return (double) intersection.size() / union.size();
    }

    /**
     * Uses regular expressions to match if a string contains characters.
     *
     * @param input
     *        The string to be checked
     * @param regex
     *        The regex
     * @return
     *         Whether a string contains specified characters
     */
    public static boolean contains(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Combines all elements in a string list into one string with the specified character as the separate.
     *
     * @param list
     *        The string list to handle
     * @param fromIndex
     *        The start index (include)
     * @param toIndex
     *        The end index (exclude)
     * @param pattern
     *        The pattern between elements
     * @return
     *         The combined string
     */
    public static String join(List<String> list, int fromIndex, int toIndex, String pattern) {
        StringBuilder builder = new StringBuilder();
        List<String> indexed = list.subList(fromIndex, toIndex);
        for (int i = 0; i < indexed.size(); i++) {
            builder.append(indexed.get(i));
            if (i < indexed.size() - 1) {
                builder.append(pattern);
            }
        }
        return builder.toString();
    }

    /**
     * Combines all elements in a string array into one string with the specified character as the separate.
     *
     * @param args
     *        The string array to handle
     * @param fromIndex
     *        The start index (include)
     * @param toIndex
     *        The end index (exclude)
     * @param pattern
     *        The pattern between elements
     * @return
     *         The combined string
     * @see
     *      #join(List, int, int, String)
     */
    public static String join(String[] args, int fromIndex, int toIndex, String pattern) {
        return join(Arrays.asList(args), fromIndex, toIndex, pattern);
    }

    /**
     * Combines all elements in a string list into one string with the specified character as the separate.
     *
     * @param list
     *        The string list to handle
     * @param fromIndex
     *        The start index (include)
     * @param pattern
     *        The pattern between elements
     * @return
     *         The combined string
     */
    public static String join(List<String> list, int fromIndex, String pattern) {
        return join(list, fromIndex, list.size(), pattern);
    }

    /**
     * Combines all elements in a string array into one string with the specified character as the separate.
     *
     * @param args
     *        The string array to handle
     * @param fromIndex
     *        The start index (include)
     * @param pattern
     *        The pattern between elements
     * @return
     *         The combined string
     * @see
     *      #join(List, int, String)
     */
    public static String join(String[] args, int fromIndex, String pattern) {
        return join(Arrays.asList(args), fromIndex, pattern);
    }

    /**
     * Repeats the specified string and combine them.
     *
     * @param string
     *        The string to be repeated
     * @param count
     *        The repeating times
     * @return
     *         A string contains repeated strings
     */
    public static String repeat(String string, int count) {
        if (string == null) {
            throw new IllegalArgumentException("Cannot repeat a null string");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(string);
        }
        return sb.toString();
    }

    public StringUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}