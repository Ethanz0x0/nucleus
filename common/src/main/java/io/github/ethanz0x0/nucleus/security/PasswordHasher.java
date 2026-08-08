package io.github.ethanz0x0.nucleus.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * A utility class for securely hashing and verifying passwords.
 * <p>
 * This class uses PBKDF2WithHmacSHA256 with a random salt.
 */
public final class PasswordHasher {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final int SALT_LENGTH = 16;

    private static final int ITERATIONS = 120000;

    private static final int KEY_LENGTH = 256;

    /**
     * Creates a password hash.
     *
     * @param password
     *      The raw password
     *
     * @return
     *      The generated password hash
     */
    public static String hash(String password) {
        checkNotNull(password, "password cannot be null");

        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);

        byte[] digest = derive(password, salt);

        return encode(salt) + ":" + encode(digest);
    }

    /**
     * Checks whether the specified password matches the stored hash.
     *
     * @param password
     *      The raw password
     *
     * @param stored
     *      The stored password hash
     *
     * @return
     *      True if the password matches
     */
    public static boolean matches(String password, String stored) {
        if (password == null || stored == null) return false;

        int separator = stored.indexOf(':');

        if (separator < 0) return false;

        try {
            byte[] salt = decode(stored.substring(0, separator));
            byte[] expected = decode(stored.substring(separator + 1));

            byte[] actual = derive(password, salt);

            return MessageDigest.isEqual(expected, actual);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static byte[] derive(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );

            return SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private static byte[] decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    private PasswordHasher() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}