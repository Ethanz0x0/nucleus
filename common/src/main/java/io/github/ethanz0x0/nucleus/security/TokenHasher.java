package io.github.ethanz0x0.nucleus.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * A utility class for hashing and verifying tokens.
 * <p>
 * Tokens are expected to already contain enough randomness, therefore no salt
 * is added.
 */
public final class TokenHasher {

    /**
     * Creates a SHA-256 token hash.
     *
     * @param token
     *      The token
     *
     * @return
     *      The token hash
     */
    public static String hash(String token) {
        checkNotNull(token, "token cannot be null");

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            return toHex(
                    digest.digest(
                            token.getBytes(StandardCharsets.UTF_8)
                    )
            );

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Checks whether a token matches the stored hash.
     *
     * @param token
     *      The raw token
     *
     * @param stored
     *      The stored hash
     *
     * @return
     *      True if the token matches
     */
    public static boolean matches(String token, String stored) {
        if (token == null || stored == null) return false;

        return MessageDigest.isEqual(
                hash(token).getBytes(StandardCharsets.UTF_8),
                stored.getBytes(StandardCharsets.UTF_8)
        );
    }

    private static String toHex(byte[] bytes) {
        StringBuilder builder =
                new StringBuilder(bytes.length * 2);

        for (byte value : bytes) {
            builder.append(String.format("%02x", value));
        }

        return builder.toString();
    }

    private TokenHasher() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}