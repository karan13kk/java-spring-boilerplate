package com.example.springboilerplate.controller;

import com.example.springboilerplate.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * Example controller demonstrating various features
 * 
 * @author Spring Boot Boilerplate
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/example")
@Tag(name = "Example", description = "Example endpoints demonstrating various features")
public class ExampleController {

    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    /**
     * Simple GET endpoint
     */
    @GetMapping
    @Operation(summary = "Get Example", description = "Returns a simple example response")
    public ResponseEntity<Map<String, Object>> getExample() {
        logger.info("Example GET endpoint called");
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot Boilerplate!");
        response.put("timestamp", java.time.LocalDateTime.now());
        response.put("status", "success");
        
        return ResponseEntity.ok(response);
    }

    /**
     * POST endpoint with validation
     */
    @PostMapping
    @Operation(summary = "Create Example", description = "Creates a new example with validation")
    public ResponseEntity<Map<String, Object>> createExample(
            @Valid @RequestBody ExampleRequest request) {
        
        logger.info("Example POST endpoint called with name: {}", request.getName());
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Example created successfully");
        response.put("name", request.getName());
        response.put("timestamp", java.time.LocalDateTime.now());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint that throws a business exception
     */
    @GetMapping("/error/business")
    public ResponseEntity<String> throwBusinessException() {
        logger.warn("Business exception endpoint called");
        throw new BusinessException("This is a business logic error", 
                HttpStatus.BAD_REQUEST, "BUSINESS_ERROR");
    }

    /**
     * Endpoint that throws a runtime exception
     */
    @GetMapping("/error/runtime")
    public ResponseEntity<String> throwRuntimeException() {
        logger.error("Runtime exception endpoint called");
        throw new RuntimeException("This is an unhandled runtime exception");
    }

    /**
     * Endpoint with path parameter validation
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(
            @PathVariable @NotBlank @Size(min = 1, max = 10) String id) {
        
        logger.info("Get by ID endpoint called with id: {}", id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("message", "Resource found");
        response.put("timestamp", java.time.LocalDateTime.now());
        
        return ResponseEntity.ok(response);
    }

    /**
     * Example request DTO with validation
     */
    public static class ExampleRequest {
        
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        private String name;
        
        @Size(max = 200, message = "Description must not exceed 200 characters")
        private String description;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
} 