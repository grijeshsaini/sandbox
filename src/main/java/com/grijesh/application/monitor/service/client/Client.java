package com.grijesh.application.monitor.service.client;

import com.grijesh.application.monitor.model.EnvironmentProperties;

/**
 * Created by grijesh.
 */
public interface Client {
    EnvironmentProperties getEnvironmentProperties(String applicationName);
    String getVersionFrom(String applicationUrl);
}
