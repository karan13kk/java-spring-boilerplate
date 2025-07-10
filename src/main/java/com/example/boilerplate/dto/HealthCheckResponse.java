package com.example.boilerplate.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckResponse {
    private String status;
    private String application;
    private String version;
    private String environment;
    private String description;
    private LocalDateTime timestamp;
} 