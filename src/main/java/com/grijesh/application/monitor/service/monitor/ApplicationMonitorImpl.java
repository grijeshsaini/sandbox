package com.grijesh.application.monitor.service.monitor;

import com.grijesh.application.monitor.model.Monitor;
import com.grijesh.application.monitor.service.filereader.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by grijesh.
 */

@Service
public class ApplicationMonitorImpl implements ApplicationMonitor {

    @Autowired
    private FileReader fileReader;

    @Override
    public List<Monitor> monitor(String envName) {
        Map<String,String> map = getEnvDetails(envName);
        map.entrySet().stream();
        return null;
    }

    private Map<String, String> getEnvDetails(String envName) {
        return fileReader.getPropertiesOf(envName);
    }
}
