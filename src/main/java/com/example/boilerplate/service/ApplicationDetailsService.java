package com.example.boilerplate.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boilerplate.dto.HealthCheckResponse;
import com.example.boilerplate.entity.ApplicationDetails;
import com.example.boilerplate.mapper.ApplicationDetailsMapper;
import com.example.boilerplate.repository.ApplicationDetailsRepository;

@Service
@Transactional
public class ApplicationDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationDetailsService.class);
    private static final String DEFAULT_APPLICATION_NAME = "boilerplate";
    private static final String DEFAULT_ENVIRONMENT = "development";

    @Autowired
    private ApplicationDetailsRepository applicationDetailsRepository;
    
    @Autowired
    private ApplicationDetailsMapper applicationDetailsMapper;

    /**
     * Get health check response with application details from database
     */
    @Transactional(readOnly = true)
    public HealthCheckResponse getHealthCheckInfo() {
        try {
            Optional<ApplicationDetails> detailsOpt = applicationDetailsRepository.findFirstByOrderByIdDesc();
            if (detailsOpt.isPresent()) {
                ApplicationDetails details = detailsOpt.get();
                logger.info("Retrieved application details from database: {} - {}", 
                           details.getApplicationName(), details.getVersion());
                return applicationDetailsMapper.toHealthCheckResponse(details);
            } else {
                logger.warn("No application details found in database for: {}", DEFAULT_APPLICATION_NAME);
                return applicationDetailsMapper.toHealthCheckResponseWithFallback(null);
            }
        } catch (Exception e) {
            logger.error("Error retrieving application details from database: {}", e.getMessage(), e);
            return applicationDetailsMapper.toHealthCheckResponseWithFallback(null);
        }
    }

    /**
     * Get current application version from database
     */
    @Transactional(readOnly = true)
    public String getCurrentVersion() {
        try {
            Optional<ApplicationDetails> detailsOpt = applicationDetailsRepository.findFirstByOrderByIdDesc();
            if (detailsOpt.isPresent()) {
                ApplicationDetails details = detailsOpt.get();
                logger.info("Retrieved version from database: {} - {}", details.getApplicationName(), details.getVersion());
                return details.getVersion();
            } else {
                logger.warn("No application details found in database for: {}", DEFAULT_APPLICATION_NAME);
                return "1.0.0"; // Default fallback version
            }
        } catch (Exception e) {
            logger.error("Error retrieving version from database: {}", e.getMessage(), e);
            return "1.0.0"; // Default fallback version
        }
    }
} 