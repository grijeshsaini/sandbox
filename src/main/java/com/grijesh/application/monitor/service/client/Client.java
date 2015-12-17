package com.grijesh.application.monitor.service.client;

import com.grijesh.application.monitor.model.EnvironmentProperties;

/**
 * Created by grijesh.
 */
interface Client {
    EnvironmentProperties getEnvironmentProperties(String applicationName);
}
