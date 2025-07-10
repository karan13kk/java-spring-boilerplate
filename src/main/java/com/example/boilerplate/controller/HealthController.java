package com.example.boilerplate.controller;

import com.example.boilerplate.dto.HealthCheckResponse;
import com.example.boilerplate.service.ApplicationDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private ApplicationDetailsService applicationDetailsService;

    @GetMapping
    public ResponseEntity<HealthCheckResponse> healthCheck() {
        logger.info("Health check requested");
        
        HealthCheckResponse response = applicationDetailsService.getHealthCheckInfo();
        
        logger.info("Health check completed successfully");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Demonstration endpoint showing CustomError usage
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validateApplicationName(@RequestParam String applicationName) {
        logger.info("Validating application name: {}", applicationName);
        
        applicationDetailsService.validateApplicationName(applicationName);
        
        return ResponseEntity.ok("Application name validation passed: " + applicationName);
    }

    /**
     * Demonstration endpoint showing business rule validation
     */
    @GetMapping("/business-rule")
    public ResponseEntity<String> checkBusinessRule(@RequestParam String environment) {
        logger.info("Checking business rule for environment: {}", environment);
        
        applicationDetailsService.checkBusinessRule(environment);
        
        return ResponseEntity.ok("Business rule validation passed for environment: " + environment);
    }
} 