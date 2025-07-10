package com.example.boilerplate.controller;

import com.example.boilerplate.service.ApplicationDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private ApplicationDetailsService applicationDetailsService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        logger.info("Health check requested");
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("application", "boilerplate");
        response.put("version", applicationDetailsService.getCurrentVersion());
        
        logger.info("Health check completed successfully");
        
        return ResponseEntity.ok(response);
    }
} 