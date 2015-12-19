package com.grijesh.application.monitor.service.filereader;

import java.util.Map;

/**
 * Created by grijesh.
 */
public interface FileReader {
    Map<String,String> getPropertiesOf(String envName);
}
