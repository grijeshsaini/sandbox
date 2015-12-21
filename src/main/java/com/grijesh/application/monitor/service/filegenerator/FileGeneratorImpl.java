package com.grijesh.application.monitor.service.filegenerator;

import com.grijesh.application.monitor.model.EnvironmentProperties;
import com.grijesh.application.monitor.service.appnameloader.AppNamesLoader;
import com.grijesh.application.monitor.service.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by grijesh.
 */

@Service
public class FileGeneratorImpl implements FileGenerator {

    @Autowired
    private Client restClient;

    @Autowired
    private AppNamesLoader appNamesLoader;

    private Map<String, String> testProperties = new HashMap<>();
    private Map<String, String> uatProperties = new HashMap<>();
    private Map<String, String> demoProperties = new HashMap<>();
    private Map<String, String> prodProperties = new HashMap<>();

    @Override
    public void createFiles() {
        Set<String> applications = getApplicationNames();

        for (String application : applications) {
            EnvironmentProperties environmentProperties = restClient.getEnvironmentProperties(application);
            populateEnvironmentMaps(application, environmentProperties);
        }

        generateFile();

    }

    private void generateFile() {
        mapToFile(testProperties, "test.properties");
        mapToFile(uatProperties, "uat.properties");
        mapToFile(demoProperties, "demo.properties");
        mapToFile(prodProperties, "prod.properties");
    }

    private void mapToFile(Map<String, String> map, String fileName) {
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null)
                properties.put(entry.getKey(), entry.getValue());
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/envfiles/" + fileName)) {
            properties.store(fileOutputStream, null);
        } catch (IOException e) {
            throw new RuntimeException("Exception occurred", e);
        }
    }

    private void populateEnvironmentMaps(String application, EnvironmentProperties environmentProperties) {
        testProperties.put(application, environmentProperties.getTest().getUrl());
        uatProperties.put(application, environmentProperties.getUat().getUrl());
        demoProperties.put(application, environmentProperties.getDemo().getUrl());
        prodProperties.put(application, environmentProperties.getProd().getUrl());
    }

    private Set<String> getApplicationNames() {
        return appNamesLoader.getApplicationNames();
    }
}
