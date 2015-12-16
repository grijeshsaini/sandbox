package com.grijesh.application.monitor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by grijesh on 17/12/15.
 */
public class EnvironmentProperties {
    @JsonProperty("TEST")
    private Test test;
    @JsonProperty("UAT")
    private Uat uat;
    @JsonProperty("DEMO")
    private Demo demo;
    @JsonProperty("PROD")
    private Prod prod;

    public EnvironmentProperties() {
    }

    public EnvironmentProperties(Test test, Uat uat, Demo demo, Prod prod) {
        this.test = test;
        this.uat = uat;
        this.demo = demo;
        this.prod = prod;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Uat getUat() {
        return uat;
    }

    public void setUat(Uat uat) {
        this.uat = uat;
    }

    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }

    public Prod getProd() {
        return prod;
    }

    public void setProd(Prod prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "EnvironmentProperties{" +
                "test=" + test +
                ", uat=" + uat +
                ", demo=" + demo +
                ", prod=" + prod +
                '}';
    }
}
