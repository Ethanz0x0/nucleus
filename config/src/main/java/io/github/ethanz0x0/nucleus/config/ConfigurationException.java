package io.github.ethanz0x0.nucleus.config;

import java.io.File;

public class ConfigurationException extends Exception {

    private final File file;

    public ConfigurationException(Throwable cause, File file) {
        super(cause);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}