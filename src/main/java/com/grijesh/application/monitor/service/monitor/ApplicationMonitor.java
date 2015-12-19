package com.grijesh.application.monitor.service.monitor;

import com.grijesh.application.monitor.model.Monitor;

import java.util.List;

/**
 * Created by grijesh.
 */
public interface ApplicationMonitor {
    List<Monitor> monitor(String envName);
}
