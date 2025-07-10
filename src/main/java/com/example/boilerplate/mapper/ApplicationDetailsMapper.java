package com.example.boilerplate.mapper;

import com.example.boilerplate.dto.HealthCheckResponse;
import com.example.boilerplate.entity.ApplicationDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface ApplicationDetailsMapper {

    /**
     * Map ApplicationDetails entity to HealthCheckResponse DTO
     */
    @Mapping(target = "status", constant = "UP")
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "currentTimestamp")
    @Mapping(target = "application", source = "applicationName")
    HealthCheckResponse toHealthCheckResponse(ApplicationDetails entity);

    /**
     * Map ApplicationDetails entity to HealthCheckResponse DTO with custom status
     */
    @Mapping(target = "timestamp", source = "timestamp", qualifiedByName = "currentTimestamp")
    @Mapping(target = "application", source = "applicationName")
    HealthCheckResponse toHealthCheckResponse(ApplicationDetails entity, String status);

    /**
     * Custom mapping for timestamp - always use current time for health check
     */
    @Named("currentTimestamp")
    default LocalDateTime currentTimestamp(LocalDateTime timestamp) {
        return LocalDateTime.now();
    }

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