package io.github.ethanz0x0.nucleus.object;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class for processing string lists.
 */
public class StringListUtil {

    /**
     * Checks if a list contains a string element, ignoring case
     *
     * @param list
     *        The list to check
     * @param pattern
     *        The string to match
     * @return
     *        Whether the list contains the string element, ignoring case
     */
    public static boolean containsIgnoreCase(List<String> list, String pattern) {
        for (String ele : list) {
            if (ele.equalsIgnoreCase(pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Splits all strings in a list by line breaker '\n' and merges the results into a new list
     *
     * @param list
     *        The list of strings to process
     * @return
     *        A new list where each original string is split by line breakers
     */
    public static List<String> splitListStringsByNewline(List<String> list) {
        List<String> resultList = new ArrayList<>();

        for (String item : list) {
            if (item.contains("\n")) {
                resultList.addAll(Arrays.asList(item.split("\n")));
            } else {
                resultList.add(item);
            }
        }

        return resultList;
    }

    /**
     * Splits all strings in a list into substrings of a specified maximum length
     * and merges them into a new list
     *
     * @param list
     *        The list of strings to process
     * @param maxLength
     *        The maximum length of each substring
     * @return
     *        A new list containing all substrings of the specified length
     */
    public static List<String> splitListStringsIntoChunks(List<String> list, int maxLength) {
        List<String> resultList = new ArrayList<>();

        for (String text : list) {
            for (int i = 0; i < text.length(); i += maxLength) {
                int end = Math.min(i + maxLength, text.length());
                resultList.add(text.substring(i, end));
            }
        }

        return resultList;
    }
}
