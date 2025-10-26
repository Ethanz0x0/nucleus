package io.github.ethanz0x0.nucleus;

import java.util.Collections;
import java.util.List;

import static io.github.ethanz0x0.nucleus.Checks.checkArgument;
import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;


/**
 * A utility class for handling pagination operation on lists.
 * <p>
 * This class provides methods to split a list into pages of a specified size and
 * retrieve either a particular page of data or the page number of a specified element.
 */
public class Pager {

    /**
     * Splits the list into pages of the specified size and returns the sublist
     * of the specified page number.
     *
     * @param data
     *        The list to be paginated
     * @param size
     *        Elements per page
     * @param page
     *        The page to retrieve (1-based index)
     * @return
     *         The sublist of the specified page, or an empty list if the page number
     *         is out of bounds
     * @param <T>
     *        The type of elements in the list
     */
    public static <T> List<T> getPage(List<T> data, int size, int page) {
        checkNotNull(data, "data list cannot be null");
        checkArgument(size > 0, "size must be larger than 0");

        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, data.size());

        if (startIndex >= data.size()) {
            return Collections.emptyList();
        } else {
            return data.subList(startIndex, endIndex);
        }
    }

    /**
     * Returns the page number in which the specified element is found,
     * when the list if split into pages of the specified size.
     *
     * @param data
     *        The list to search
     * @param obj
     *        The element to find
     * @param size
     *        Elements per page
     * @return
     *         The 1-based page number containing the element, or -1 if not found
     * @param <T>
     *        The type of elements in the list
     */
    public static <T> int getPageAt(List<T> data, T obj, int size) {
        checkNotNull(data, "data list cannot be null");
        checkArgument(size > 0, "size must be larger than 0");

        int totalPage = calculatePages(data.size(), size);
        for (int i = 0; i < totalPage; i++) {
            List<T> current = getPage(data, size, i);
            if (current.contains(obj)) return i;
        }
        return -1;
    }

    /**
     * Calculates the total nu,ber of pages needed to hold a give number of elements,
     * based on the specified page size.
     *
     * @param amount
     *        The total number of elements
     * @param size
     *        Elements per page
     * @return
     *         The total number of pages
     */
    public static int calculatePages(int amount, int size) {
        checkArgument(size > 0, "size must be larger than 0");
        return (int) Math.ceil((double) amount / size);
    }

    private Pager() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
