package com.example.boilerplate.repository;

import com.example.boilerplate.entity.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long> {

    /**
     * Find application details by application name
     */
    Optional<ApplicationDetails> findFirstByOrderByIdDesc();

    /**
     * Find application details by application name and environment
     */
    Optional<ApplicationDetails> findByApplicationNameAndEnvironment(String applicationName, String environment);

    /**
     * Custom query to find application details by name with specific environment
     */
    @Query("SELECT ad FROM ApplicationDetails ad WHERE ad.applicationName = :applicationName AND ad.environment = :environment")
    Optional<ApplicationDetails> findByApplicationNameAndEnvironmentCustom(@Param("applicationName") String applicationName, 
                                                                         @Param("environment") String environment);
} 