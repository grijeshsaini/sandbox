package com.grijesh.application.monitor.model;

/**
 * Created by grijesh on 17/12/15.
 */
public abstract class AbstractEnv {

    private String expectedName;
    private String url;

    public String getExpectedName() {
        return expectedName;
    }

    public void setExpectedName(String expectedName) {
        this.expectedName = expectedName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AbstractEnv{" +
                "expectedName='" + expectedName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
