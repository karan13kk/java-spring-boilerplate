package com.example.boilerplate.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "application_details")
public class ApplicationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_name", unique = true, nullable = false, length = 100)
    private String applicationName;

    @Column(name = "version", nullable = false, length = 50)
    private String version;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "environment", length = 50)
    private String environment;

    @Column(name = "build_number", length = 50)
    private String buildNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Default constructor
    public ApplicationDetails() {
    }

    // Constructor with required fields
    public ApplicationDetails(String applicationName, String version) {
        this.applicationName = applicationName;
        this.version = version;
    }

    // Constructor with all fields
    public ApplicationDetails(String applicationName, String version, String description, String environment, String buildNumber) {
        this.applicationName = applicationName;
        this.version = version;
        this.description = description;
        this.environment = environment;
        this.buildNumber = buildNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ApplicationDetails{" +
                "id=" + id +
                ", applicationName='" + applicationName + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", environment='" + environment + '\'' +
                ", buildNumber='" + buildNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 