package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.model.Monitor;
import com.grijesh.application.monitor.service.monitor.ApplicationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/{envName}")
    public String monitorTest(@PathVariable("envName") String envName, Map<String, Object> model) {
        return monitorEnv(envName, model);
    }

    /**
     * Hack to make default environment as Test
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String monitor(Map<String, Object> model) {
        return monitorEnv("test", model);
    }

    private String monitorEnv(@PathVariable("envName") String envName, Map<String, Object> model) {
        List<Monitor> appList = getMonitor(envName.toLowerCase());
        long downApps = countDownStatus(appList);
        model.put("envName", envName.toUpperCase());
        model.put("apps", appList);
        model.put("down", downApps);
        model.put("up", appList.size() - downApps);
        return "monitor";
    }

    /*@RequestMapping("/prod")
    public String monitorProd(Map<String, Object> model) {
        List<Monitor> appList = getMonitor("Test");
        long downApps = countDownStatus(appList);
        model.put("envName", "Test");
        model.put("apps", appList);
        model.put("down",downApps);
        model.put("up",appList.size()-downApps);
        return "monitor";
    }

    @RequestMapping("/uat")
    public String monitorUat(Map<String, Object> model) {
        List<Monitor> appList = getMonitor("Test");
        long downApps = countDownStatus(appList);
        model.put("envName", "Test");
        model.put("apps", appList);
        model.put("down",downApps);
        model.put("up",appList.size()-downApps);
        return "monitor";
    }

    @RequestMapping("/demo")
    public String monitorDemp(Map<String, Object> model) {
        List<Monitor> appList = getMonitor("Test");
        long downApps = countDownStatus(appList);
        model.put("envName", "Test");
        model.put("apps", appList);
        model.put("down",downApps);
        model.put("up",appList.size()-downApps);
        return "monitor";
    }*/
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

    private long countDownStatus(List<Monitor> appList) {
        return appList.stream().filter(app -> app.getStatus().equals("DOWN")).count();
    }

}
