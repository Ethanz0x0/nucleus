package io.github.ethanz0x0.nucleus.object;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing human-readable duration strings into {@link Duration}.
 * <p>
 * Supported format examples:
 * <ul>
 *   <li>"1y2M3w4d5h6m7s" → 1 year, 2 months, 3 weeks, 4 days, 5 hours, 6 minutes, 7 seconds</li>
 *   <li>"2h 30m" → 2 hours and 30 minutes</li>
 * </ul>
 * <p>
 * Notes:
 * <ul>
 *   <li>Years are approximated as 365 days</li>
 *   <li>Months are approximated as 30 days</li>
 *   <li>Weeks are 7 days</li>
 * </ul>
 */
public class DurationUtil {

    // Regex pattern for matching duration components
    private static final Pattern DURATION_PATTERN = Pattern.compile(
            "(?:(\\d+)\\s*y)?\\s*" +   // years
            "(?:(\\d+)\\s*M)?\\s*" +   // months
            "(?:(\\d+)\\s*w)?\\s*" +   // weeks
            "(?:(\\d+)\\s*d)?\\s*" +   // days
            "(?:(\\d+)\\s*h)?\\s*" +   // hours
            "(?:(\\d+)\\s*m)?\\s*" +   // minutes
            "(?:(\\d+)\\s*s)?"         // seconds
    );

    /**
     * Parses a human-readable duration string into a {@link Duration}.
     *
     * @param input
     *        The duration string to parse. Examples:
     *        <ul>
     *          <li>"1y2M3w4d5h6m7s"</li>
     *          <li>"2h 30m"</li>
     *        </ul>
     *
     * @return
     *         A {@link Duration} representing the parsed time span
     *
     * @throws IllegalArgumentException
     *         If the input does not match the expected duration format
     */
    public static Duration parseDuration(String input) {
        Matcher matcher = DURATION_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration format: " + input);
        }

        int years   = parseGroupAsInt(matcher, 1);
        int months  = parseGroupAsInt(matcher, 2);
        int weeks   = parseGroupAsInt(matcher, 3);
        int days    = parseGroupAsInt(matcher, 4);
        int hours   = parseGroupAsInt(matcher, 5);
        int minutes = parseGroupAsInt(matcher, 6);
        int seconds = parseGroupAsInt(matcher, 7);

        long totalDays = years * 365L + months * 30L + weeks * 7L + days;

        return Duration.ofDays(totalDays)
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
    }

    /**
     * Safely parses a regex group value as an integer, defaulting to {@code 0} if the group is {@code null}.
     *
     * @param matcher
     *        The matcher containing the captured groups
     * @param group
     *        The group index to parse
     *
     * @return
     *         The integer value of the group, or {@code 0} if absent
     */
    private static int parseGroupAsInt(Matcher matcher, int group) {
        String value = matcher.group(group);
        return (value == null) ? 0 : Integer.parseInt(value);
    }

    private DurationUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
