package com.grijesh.application.monitor.model;

/**
 * Created by grijesh.
 */
public class Monitor implements Comparable<Monitor> {

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
        return o.getStatus().compareTo(this.getStatus());
    }
}
