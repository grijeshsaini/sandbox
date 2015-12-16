package com.grijesh.application.monitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by grijesh on 17/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Uat extends AbstractEnv {

    public Uat() {
    }
}
