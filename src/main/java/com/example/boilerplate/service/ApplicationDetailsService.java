package com.example.boilerplate.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boilerplate.entity.ApplicationDetails;
import com.example.boilerplate.repository.ApplicationDetailsRepository;

@Service
@Transactional
public class ApplicationDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationDetailsService.class);
    private static final String DEFAULT_APPLICATION_NAME = "boilerplate";
    private static final String DEFAULT_ENVIRONMENT = "development";

    private final ApplicationDetailsRepository applicationDetailsRepository;

    @Autowired
    public ApplicationDetailsService(ApplicationDetailsRepository applicationDetailsRepository) {
        this.applicationDetailsRepository = applicationDetailsRepository;
    }

    /**
     * Get current application version from database
     */
    @Transactional(readOnly = true)
    public String getCurrentVersion() {
        try {
            Optional<ApplicationDetails> detailsOpt = applicationDetailsRepository.findByApplicationName(DEFAULT_APPLICATION_NAME);
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