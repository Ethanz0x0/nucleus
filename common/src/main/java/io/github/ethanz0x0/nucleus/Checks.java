package io.github.ethanz0x0.nucleus;

/**
 * Utility class for performing common validation checks on arguments, states, and references.
 */
public class Checks {

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression
     *        A boolean expression
     * @throws IllegalArgumentException
     *         If {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression
     *        A boolean expression
     * @param message
     *        The exception message to use if the check fails; will be converted to a string using {@link String#valueOf(Object)}
     * @throws IllegalArgumentException
     *         If {@code expression} is false
     */
    public static void checkArgument(boolean expression, Object message) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(message));
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance.
     *
     * @param expression
     *        A boolean expression
     * @throws IllegalStateException
     *         If {@code expression} is false
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance.
     *
     * @param expression
     *        A boolean expression
     * @param message
     *        The exception message to use if the check fails; will be converted to a string using {@link String#valueOf(Object)}
     * @throws IllegalStateException
     *         If {@code expression} is false
     */
    public static void checkState(boolean expression, Object message) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(message));
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *        An object reference
     * @param <T>
     *        The type of the reference
     * @return
     *         The non-null reference that was validated
     * @throws NullPointerException
     *         If {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference
     *        An object reference
     * @param message
     *        The exception message to use if the check fails; will be converted to a string using {@link String#valueOf(Object)}
     * @param <T>
     *        The type of the reference
     * @return
     *         The non-null reference that was validated
     * @throws NullPointerException
     *         If {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, Object message) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(message));
        }
        return reference;
    }
}