package com.grijesh.application.monitor.service.filegenerator;

import com.grijesh.application.monitor.model.EnvironmentProperties;
import com.grijesh.application.monitor.service.client.Client;
import com.grijesh.application.monitor.service.urlgenerator.UrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by grijesh.
 */

@Service
public class FileGeneratorImpl implements FileGenerator {

    @Autowired
    private Client restClient;

    @Autowired
    private UrlGenerator urlGenerator;

    private Map<String, String> testProperties = new HashMap<>();
    private Map<String, String> uatProperties = new HashMap<>();
    private Map<String, String> demoProperties = new HashMap<>();
    private Map<String, String> prodProperties = new HashMap<>();

    @Override
    public void createFiles() {
        Set<String> applications = getApplicationNames();

        for (String application : applications) {
            EnvironmentProperties environmentProperties = restClient.getEnvironmentProperties(application);
            populateEnviornmentMaps(application, environmentProperties);
        }

        generateFile();

    }

    private void generateFile() {

    }

    private void populateEnviornmentMaps(String application, EnvironmentProperties environmentProperties) {
        testProperties.put(application, environmentProperties.getTest().getUrl());
        uatProperties.put(application, environmentProperties.getUat().getUrl());
        demoProperties.put(application, environmentProperties.getDemo().getUrl());
        prodProperties.put(application, environmentProperties.getProd().getUrl());
    }

    private Set<String> getApplicationNames() {
        return urlGenerator.getApplicationNames();
    }
}
