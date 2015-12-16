package com.grijesh.application.monitor.util;

import com.grijesh.application.monitor.model.EnvironmentProperties;

/**
 * Created by grijesh.
 */
public interface Client {
    EnvironmentProperties getEnvironmentProperties(String applicationName);
}
