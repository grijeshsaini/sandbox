package com.grijesh.application.monitor.service.appnameloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * This class will read the application names from
 * app-names.properties and generate the urls for the respective application
 * and will create a new file with all details
 * <p>
 * Created by grijesh.
 */
@Service
public class AppNamesLoader {

    private static final Logger logger = LoggerFactory.getLogger(AppNamesLoader.class);

    private final static String APP_NAMES_FILES = "app-names.properties";

    @Autowired
    private ResourceLoader resourceLoader;

    public Set<String> getApplicationNames() {
        logger.info("method=getApplicationNames");
        Set<String> applications = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getFile()))) {
            String name;
            while ((name = br.readLine()) != null) {
                applications.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Not able to create url list", e);
        }

        return applications;
    }


    private InputStream getFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + APP_NAMES_FILES);
        return resource.getInputStream();
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
