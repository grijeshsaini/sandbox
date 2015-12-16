package com.grijesh.application.monitor.util;

import com.grijesh.application.monitor.model.EnvironmentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This class is a generic client
 *
 * Created by grijesh.
 */

@Component
public class RestClient implements Client{

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    @Value("${monitor.version.url}")
    private String url;

    @Override
    public EnvironmentProperties getEnvironmentProperties(String applicationName) {
        logger.info("method=getEnvironmentProperties application name {}",applicationName);
        RestTemplate restTemplate = getRestTemplate();
        url = String.format(url,applicationName);
        logger.info("URL:{}",url);
        return restTemplate.getForObject(url,EnvironmentProperties.class);
    }

    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
