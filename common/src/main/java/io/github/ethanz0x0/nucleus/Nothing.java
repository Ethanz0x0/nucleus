package io.github.ethanz0x0.nucleus;

/**
 * A singleton class of representing the absence of a value.
 * <p>
 * This class can be used as a placeholder where a value is required but
 * there is nothing meaningful to provide.
 */
public class Nothing {

    public static Nothing NOTHING = new Nothing();

    private Nothing() {
    }
}
