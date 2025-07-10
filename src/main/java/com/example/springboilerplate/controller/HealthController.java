package com.example.springboilerplate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health check controller providing application health status
 * 
 * @author Spring Boot Boilerplate
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "Health check endpoints for monitoring application status")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    /**
     * Basic health check endpoint
     * 
     * @return Health status with timestamp
     */
    @GetMapping
    @Operation(summary = "Basic Health Check", description = "Returns basic application health status")
    public ResponseEntity<Map<String, Object>> health() {
        logger.debug("Health check requested");
        
        Map<String, Object> healthStatus = new HashMap<>();
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", LocalDateTime.now());
        healthStatus.put("application", "Spring Boot Boilerplate");
        healthStatus.put("version", "1.0.0");
        
        logger.info("Health check completed successfully");
        return ResponseEntity.ok(healthStatus);
    }

    /**
     * Detailed health check endpoint
     * 
     * @return Detailed health status including system information
     */
    @GetMapping("/detailed")
    @Operation(summary = "Detailed Health Check", description = "Returns detailed health status with system information")
    public ResponseEntity<Map<String, Object>> detailedHealth() {
        logger.debug("Detailed health check requested");
        
        Map<String, Object> detailedHealth = new HashMap<>();
        detailedHealth.put("status", "UP");
        detailedHealth.put("timestamp", LocalDateTime.now());
        detailedHealth.put("application", "Spring Boot Boilerplate");
        detailedHealth.put("version", "1.0.0");
        
        // System information
        Map<String, Object> systemInfo = new HashMap<>();
        systemInfo.put("javaVersion", System.getProperty("java.version"));
        systemInfo.put("osName", System.getProperty("os.name"));
        systemInfo.put("osVersion", System.getProperty("os.version"));
        systemInfo.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        systemInfo.put("totalMemory", Runtime.getRuntime().totalMemory());
        systemInfo.put("freeMemory", Runtime.getRuntime().freeMemory());
        
        detailedHealth.put("system", systemInfo);
        
        logger.info("Detailed health check completed successfully");
        return ResponseEntity.ok(detailedHealth);
    }
} 