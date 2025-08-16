package io.github.ethanz0x0.nucleus.object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * A utility class for processing date.
 */
public class DateUtil {

    /**
     * Formats the provided {@code Date} as a string from the timezone.
     *
     * @param pattern
     *        The format
     * @param date
     *        The {@code Date} instance
     * @param timezone
     *        The timezone
     * @return
     *         The formatted date string
     */
    public static String format(String pattern, Date date, TimeZone timezone) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        if (timezone != null) {
            format.setTimeZone(timezone);
        }
        return format.format(date);
    }

    /**
     * Formats the provided Date as a string from the time zone.
     *
     * @param pattern
     *        The format
     * @param timezone
     *        The timezone
     * @return
     *         The formatted date string
     *
     * @see
     *      #format(String, Date, TimeZone)
     */
    public static String formatCurrent(String pattern, TimeZone timezone) {
        return format(pattern, new Date(), timezone);
    }
    
    private DateUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}