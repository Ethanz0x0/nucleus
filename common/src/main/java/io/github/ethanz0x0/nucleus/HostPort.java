package io.github.ethanz0x0.nucleus;

import java.util.regex.Pattern;

/**
 * Provides methods for parsing IP address into host and port.
 * IPv4 and IPv6 are supported.
 */
public class HostPort {

    private static final int MAX_PORT = 65535;

    // IPv4 pattern: 0.0.0.0 - 255.255.255.255
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");

    // IPv6 pattern: [::1] style
    private static final Pattern IPV6_PATTERN = Pattern.compile("^\\[([a-fA-F0-9:]+)]$");

    // Strict hostname pattern according to RFC 1035
    // Each label: 1-63 chars, letters/digits/hyphen, no hyphen at start/end
    // Total length <= 253
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile(
            "^(?=.{1,253}$)([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)*" +
                    "([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)$"
    );


    /**
     * Parses a IPv4 or IPv6 string as a {@code HostPort} instance.
     *
     * @param input
     *        IPv4 or IPv6 string to be parsed
     * @return
     *         The {@code HostPort} instance
     */
    public static HostPort fromString(String input) {
        String trimmedInput = input.trim();

        String hostPart;
        String portPart = null;

        if (trimmedInput.startsWith("[") && trimmedInput.endsWith("]:")) {
            int endIndex = trimmedInput.indexOf(']');
            hostPart = trimmedInput.substring(1, endIndex);
            portPart = trimmedInput.substring(endIndex + 2);
        } else if (trimmedInput.contains(":") && trimmedInput.indexOf(":") == trimmedInput.lastIndexOf(":")) {
            String[] parts = trimmedInput.split(":");
            hostPart = parts[0];
            portPart = parts[1];
        } else {
            hostPart = trimmedInput;
        }

        if (!isValidIPv4(hostPart) && !isValidIPv6(hostPart) && !isValidHostname(hostPart)) {
            throw new IllegalArgumentException("Invalid host: " + hostPart);
        }

        Integer port = null;
        if (portPart != null) {
            try {
                port = Integer.parseInt(portPart);
                if (port < 0 || port > MAX_PORT) {
                    throw new IllegalArgumentException("Port out of range: " + portPart);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid port: " + portPart);
            }
        }

        return new HostPort(hostPart, port);
    }

    private static boolean isValidIPv4(String host) {
        if (!IPV4_PATTERN.matcher(host).matches()) return false;
        String[] parts = host.split("\\.");
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) return false;
        }
        return true;
    }

    private static boolean isValidIPv6(String host) {
        return IPV6_PATTERN.matcher(host).matches();
    }


    private static boolean isValidHostname(String host) {
        return HOSTNAME_PATTERN.matcher(host).matches();
    }

    private final String host;
    private final Integer port;

    private HostPort(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public Integer getPortOrDefault(int def) {
        return port == null ? def : port;
    }

    @Override
    public String toString() {
        if (port != null) {
            return isValidIPv6(host) ? "[" + host + "]:" + port : host + ":" + port;
        }
        return host;
    }
}
