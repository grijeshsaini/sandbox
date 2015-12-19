package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.model.Monitor;
import com.grijesh.application.monitor.service.monitor.ApplicationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by grijesh.
 */
@RestController
@RequestMapping("/monitor")
public class ApplicationMonitorController {

    @Autowired
    private ApplicationMonitor applicationMonitor;

    @RequestMapping("/test")
    public ResponseEntity<Object> monitorTest() {
        System.out.println("coming");
        return getListOfMonitor("Test");
    }

    @RequestMapping("/uat")
    public ResponseEntity<Object> monitorUat() {
        return getListOfMonitor("Uat");
    }

    private ResponseEntity<Object> getListOfMonitor(String envName) {
        try {
            return new ResponseEntity<>(applicationMonitor.monitor(envName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
