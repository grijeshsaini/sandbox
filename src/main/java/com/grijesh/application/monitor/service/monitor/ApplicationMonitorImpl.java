package com.grijesh.application.monitor.service.monitor;

import com.grijesh.application.monitor.model.Monitor;
import com.grijesh.application.monitor.service.client.RestClient;
import com.grijesh.application.monitor.service.filereader.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by grijesh.
 */

@Service
public class ApplicationMonitorImpl implements ApplicationMonitor {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private RestClient restClient;

    @Override
    public List<Monitor> monitor(String envName) {
        Map<String, String> map = getEnvDetails(envName);
        return map.entrySet().stream()
                .map((e) -> setStatus(e.getKey(), restClient.getVersionFrom(e.getValue())))
                .sorted()
                .collect(Collectors.toList());
    }

    public Monitor setStatus(String key, String status) {
        Monitor monitor = new Monitor();
        monitor.setStatus(status);
        monitor.setAppName(key);
        return monitor;
    }

    private Map<String, String> getEnvDetails(String envName) {
        return fileReader.getPropertiesOf(envName);
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }
}
