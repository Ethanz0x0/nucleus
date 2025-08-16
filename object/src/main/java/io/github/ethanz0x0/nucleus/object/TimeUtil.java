package io.github.ethanz0x0.nucleus.object;

/**
 * Utility class for common time and date formatting operations.
 */
public final class TimeUtil {

    /**
     * Formats a total number of seconds into a clock-style string.
     * <p>
     * Examples:
     * <ul>
     *   <li>65 → {@code "01:05"}</li>
     *   <li>3665 → {@code "01:01:05"}</li>
     * </ul>
     *
     * @param totalSeconds
     *        The total number of seconds to format
     *
     * @return
     *         A formatted time string in either {@code mm:ss} or {@code HH:mm:ss} format
     */
    public static String formatSecondsAsClock(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return (hours > 0)
                ? String.format("%02d:%02d:%02d", hours, minutes, seconds)
                : String.format("%02d:%02d", minutes, seconds);
    }

    private TimeUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
