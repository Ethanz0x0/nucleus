package io.github.ethanz0x0.nucleus.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import static io.github.ethanz0x0.nucleus.Checks.checkNotNull;

/**
 * A utility class for creating and verifying HMAC signatures.
 */
public final class HmacHasher {

    private static final String ALGORITHM = "HmacSHA256";

    /**
     * Creates a HMAC signature.
     *
     * @param value
     *      The value to sign
     *
     * @param secret
     *      The secret key
     *
     * @return
     *      The generated signature
     */
    public static String hash(String value, String secret) {
        checkNotNull(value, "value cannot be null");
        checkNotNull(secret, "secret cannot be null");

        try {
            Mac mac = Mac.getInstance(ALGORITHM);

            mac.init(
                    new SecretKeySpec(
                            secret.getBytes(StandardCharsets.UTF_8),
                            ALGORITHM
                    )
            );

            return Base64.getEncoder()
                    .encodeToString(
                            mac.doFinal(
                                    value.getBytes(StandardCharsets.UTF_8)
                            )
                    );

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Checks whether a value matches a HMAC signature.
     *
     * @param value
     *      The original value
     *
     * @param secret
     *      The secret key
     *
     * @param signature
     *      The stored signature
     *
     * @return
     *      True if the signature matches
     */
    public static boolean matches(
            String value,
            String secret,
            String signature
    ) {
        if (value == null || secret == null || signature == null) {
            return false;
        }

        return MessageDigest.isEqual(
                hash(value, secret)
                        .getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
        );
    }

    private HmacHasher() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}