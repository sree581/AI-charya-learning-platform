package com.aicharya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Is this annotation present?
@RestController
// Is this path correct?
@RequestMapping("/api/student")
public class StudentController {

    // For now, let's comment this out to keep it simple
    // @Autowired
    // private RecommendationService recommendationService;

    // Is this mapping correct?
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Aicharya student controller is alive!");
    }
}