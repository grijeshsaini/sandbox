package com.grijesh.application.monitor.controller;

import com.grijesh.application.monitor.service.filegenerator.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by grijesh.
 */

//Todo this controller has to be converted into a one time job.
@RestController
@RequestMapping("/data")
public class DataGeneratorController {

    @Autowired
    private FileGenerator fileGenerator;

    @RequestMapping("/generate")
    public ResponseEntity<String> generateData() {
        try {
            fileGenerator.createFiles();
        } catch (Exception e) {
            return new ResponseEntity<>("Error while generating file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Data successfully generated", HttpStatus.OK);
    }
}
