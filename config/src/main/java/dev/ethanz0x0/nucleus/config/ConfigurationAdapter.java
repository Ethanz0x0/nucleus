package dev.ethanz0x0.nucleus.config;

import dev.ethanz0x0.nucleus.config.adapters.JsonConfigurationAdapter;
import dev.ethanz0x0.nucleus.config.adapters.YamlConfigurationAdapter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class ConfigurationAdapter {

    private static final Map<Class<? extends ConfigurationAdapter>, ConfigurationAdapter> registeredAdapters = new HashMap<>();

    static {
        registerAdapter(YamlConfigurationAdapter.class, YamlConfigurationAdapter.INSTANCE);
        registerAdapter(JsonConfigurationAdapter.class, JsonConfigurationAdapter.INSTANCE);
    }

    public static ConfigurationAdapter getAdapter(Class<? extends ConfigurationAdapter> clazz) {
        return registeredAdapters.get(clazz);
    }

    public static void registerAdapter(Class<? extends ConfigurationAdapter> clazz, ConfigurationAdapter adapter) {
        registeredAdapters.put(clazz, adapter);
    }

    public static Map<Class<? extends ConfigurationAdapter>, ConfigurationAdapter> getAdapters() {
        return registeredAdapters;
    }

    public abstract Configuration load(File file) throws Exception;

    public abstract Configuration load(InputStream in);

    public abstract void save(Configuration config, File file) throws IOException;

    public abstract void save(Configuration config, Writer writer);

}