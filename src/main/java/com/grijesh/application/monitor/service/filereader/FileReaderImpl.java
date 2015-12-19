package com.grijesh.application.monitor.service.filereader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by grijesh.
 */
@Service
public class FileReaderImpl implements FileReader {

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Map<String, String> getPropertiesOf(String envName) {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();

        try (InputStream inputStream = resourceLoader.getResource("classpath:envfiles/" + envName + ".properties").getInputStream();) {
            properties.load(inputStream);
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.get(key).toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception occurred", e);
        }
        return map;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}

