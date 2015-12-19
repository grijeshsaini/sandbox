package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.service.filegenerator.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by grijesh.
 */

//Todo this controller has to be converted into a one time job.
@RestController
public class DataGeneratorController {

    @Autowired
    private FileGenerator fileGenerator;

    @RequestMapping("/generate")
    public String generateData(){
        fileGenerator.createFiles();
        return "Done";
    }
}
