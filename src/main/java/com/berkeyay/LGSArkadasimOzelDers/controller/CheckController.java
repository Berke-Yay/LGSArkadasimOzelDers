package com.berkeyay.LGSArkadasimOzelDers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @GetMapping("/check")
    public ResponseEntity<String> check(){
        return ResponseEntity.ok("Service is running");
    }
}
