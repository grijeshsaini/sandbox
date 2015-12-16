package com.grijesh.application.monitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by grijesh on 17/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Test extends AbstractEnv {

    public Test() {
    }
}
