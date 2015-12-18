package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.service.filegenerator.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by grijesh on 19/12/15.
 */

@RestController
public class DataGeneratorController {

    @Autowired
    private FileGenerator fileGenerator;

    @RequestMapping("/generate")
    public void generateData(){
        fileGenerator.createFiles();
    }
}
