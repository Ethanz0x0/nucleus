package io.github.ethanz0x0.nucleus.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * A utility class for creating and verifying salted SHA-256 hashes.
 * <p>
 * This class adds a random salt before hashing to prevent rainbow table attacks.
 */
public final class Sha256Hasher {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final int SALT_LENGTH = 16;

    /**
     * Creates a salted SHA-256 hash.
     *
     * @param value
     *      The value to hash
     *
     * @return
     *      The generated hash
     */
    public static String hash(String value) {
        checkNotNull(value, "value cannot be null");

        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);

        return encode(salt) + ":" + encode(digest(value, salt));
    }

    /**
     * Checks whether a value matches the stored hash.
     *
     * @param value
     *      The raw value
     *
     * @param stored
     *      The stored hash
     *
     * @return
     *      True if the value matches
     */
    public static boolean matches(String value, String stored) {
        if (value == null || stored == null) return false;

        int separator = stored.indexOf(':');

        if (separator < 0) return false;

        try {
            byte[] salt = decode(stored.substring(0, separator));
            byte[] expected = decode(stored.substring(separator + 1));

            return MessageDigest.isEqual(
                    expected,
                    digest(value, salt)
            );

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static byte[] digest(String value, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(salt);

            return md.digest(
                    value.getBytes(StandardCharsets.UTF_8)
            );

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private static byte[] decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    private Sha256Hasher() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}