package com.grijesh.application.monitor.model;

import java.util.Comparator;

/**
 * Created by grijesh.
 */
public class Monitor implements Comparable<Monitor>{

    private String appName;

    private String status;

    private String splunkUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSplunkUrl() {
        return splunkUrl;
    }

    public void setSplunkUrl(String splunkUrl) {
        this.splunkUrl = splunkUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public int compareTo(Monitor o) {
        if(this.getStatus().equals("DOWN"))
            return -1;
        else if(!this.getAppName().equals("DOWN"))
            return 1;
        else
            return 0;
    }
}
