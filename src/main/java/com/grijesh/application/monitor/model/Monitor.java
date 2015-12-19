package com.grijesh.application.monitor.model;

/**
 * Created by grijesh.
 */
public class Monitor {

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
}
