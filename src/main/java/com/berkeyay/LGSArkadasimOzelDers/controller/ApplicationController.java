package com.berkeyay.LGSArkadasimOzelDers.controller;

import com.berkeyay.LGSArkadasimOzelDers.entity.Application;
import com.berkeyay.LGSArkadasimOzelDers.entity.ApplicationRequest;
import com.berkeyay.LGSArkadasimOzelDers.service.ApplicationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping(value = "/applications")
    public ResponseEntity<Application> saveApplication(@ModelAttribute ApplicationRequest applicationRequest) throws MessagingException, IOException {
        Application application = applicationService.saveApplication(applicationRequest);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }
}
