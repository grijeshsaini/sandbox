package com.grijesh.application.monitor.service.urlgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class will read the application names from
 * app-names.properties and generate the urls for the respective application
 * and will create a new file with all details
 * <p>
 * Created by grijesh.
 */
@Component
public class UrlGenerator {

    private static final Logger logger = LoggerFactory.getLogger(UrlGenerator.class);

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
