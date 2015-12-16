package com.grijesh.application.monitor.util;

import com.grijesh.application.monitor.model.EnvironmentProperties;

/**
 * Created by grijesh.
 */
interface Client {
    EnvironmentProperties getEnvironmentProperties(String applicationName);
}
