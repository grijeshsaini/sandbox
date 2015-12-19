package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.model.Monitor;
import com.grijesh.application.monitor.service.monitor.ApplicationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by grijesh.
 */
@Controller
@RequestMapping("/monitor")
public class ApplicationMonitorController {

    @Autowired
    private ApplicationMonitor applicationMonitor;

    @RequestMapping("/test")
    public String monitorTest(Map<String, Object> model) {
        model.put("envName", "Test");
        model.put("testApps", getMonitor("Test"));
        return "monitor";
    }

    @RequestMapping("/prod")
    public String monitorProd(Map<String, Object> model) {
        model.put("envName", "Prod");
        model.put("testApps", getMonitor("Prod"));
        return "monitor";
    }

    @RequestMapping("/uat")
    public String monitorUat(Map<String, Object> model) {
        model.put("envName", "Uat");
        model.put("testApps", getMonitor("Uat"));
        return "monitor";
    }

    @RequestMapping("/demo")
    public String monitorDemp(Map<String, Object> model) {
        model.put("envName", "Demo");
        model.put("testApps", getMonitor("Demo"));
        return "monitor";
    }
    /*@RequestMapping("/test")
    public ResponseEntity<Object> monitorTest() {
        return getListOfMonitor("Test");
    }

    @RequestMapping("/uat")
    public ResponseEntity<Object> monitorUat() {
        return getListOfMonitor("Uat");
    }

    private ResponseEntity<Object> getListOfMonitor(String envName) {
        try {
            return new ResponseEntity<>(getMonitor(envName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    private List<Monitor> getMonitor(String envName) {
        return applicationMonitor.monitor(envName);
    }

}
