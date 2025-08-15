package dev.ethanz0x0.nucleus.object.format;

import java.util.List;

public abstract class Format {

    protected String nullToEmpty(String input) {
        if (input == null) {
            return "";
        }
        return input;
    }

    public abstract String format(String input);

    public abstract List<String> format(List<String> input);
}
