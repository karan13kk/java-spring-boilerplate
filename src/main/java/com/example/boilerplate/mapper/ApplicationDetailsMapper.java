package com.example.boilerplate.mapper;

import com.example.boilerplate.dto.HealthCheckResponse;
import com.example.boilerplate.entity.ApplicationDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ApplicationDetailsMapper {

    /**
     * Map ApplicationDetails entity to HealthCheckResponse DTO
     */
    @Mapping(target = "status", constant = "UP")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "application", source = "applicationName")
    HealthCheckResponse toHealthCheckResponse(ApplicationDetails entity);

    /**
     * Map ApplicationDetails entity to HealthCheckResponse DTO with custom status
     */
    @Mapping(target = "status", source = "status")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "application", source = "entity.applicationName")
    HealthCheckResponse toHealthCheckResponse(ApplicationDetails entity, String status);

    /**
     * Map ApplicationDetails entity to HealthCheckResponse DTO with fallback values
     */
    default HealthCheckResponse toHealthCheckResponseWithFallback(ApplicationDetails entity) {
        if (entity == null) {
            return new HealthCheckResponse(
                "UP",
                "boilerplate",
                "1.0.0",
                "development",
                "In memory db not yet set",
                LocalDateTime.now()
            );
        }
        
        return toHealthCheckResponse(entity);
    }
} 