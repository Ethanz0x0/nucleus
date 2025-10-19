package io.github.ethanz0x0.nucleus.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.LinkedHashMap;

public class FileConfiguration extends Configuration {

    private final File file;
    private final ConfigurationAdapter adapter;
    private boolean isLoading = false;

    public FileConfiguration(File file, InputStream source, Class<? extends ConfigurationAdapter> adapter)
            throws ConfigurationException {
        super(new LinkedHashMap<>());
        this.file = file;
        this.adapter = ConfigurationAdapter.getAdapter(adapter);

        if (this.adapter == null) {
            throw new ConfigurationException(new IllegalArgumentException("No such adapter registered: " + adapter.getSimpleName()), file);
        }

        if (!file.exists()) {
            try {
                if (source == null) {
                    file.createNewFile();
                } else {
                    Files.copy(source, file.toPath());
                }
            } catch (IOException e) {
                throw new ConfigurationException(e, file);
            }
        }

        loadConfiguration();
    }

    public FileConfiguration(File file, Class<? extends ConfigurationAdapter> adapter)
            throws ConfigurationException {
        this(file, null, adapter);
    }

    private void loadConfiguration() {
        isLoading = true;
        try {
            load(this.adapter.load(file).getMap());
        }  catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            isLoading = false;
        }
    }

    public void save() {
        if (isLoading) {
            return;
        }
        try {
            adapter.save(this, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        loadConfiguration();
    }
}