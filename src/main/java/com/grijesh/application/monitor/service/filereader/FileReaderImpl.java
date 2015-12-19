package com.grijesh.application.monitor.service.filereader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by grijesh.
 */
public class FileReaderImpl implements FileReader {

    @Override
    public Map<String, String> getPropertiesOf(String envName) {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();

        try (InputStream inputStream = FileReaderImpl.class.getClassLoader().getResourceAsStream(envName + ".properties")) {
            properties.load(inputStream);
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.get(key).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception occurred", e);
        }
        return map;
    }
}

