package com.example.springboilerplate.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom business exception for application-specific errors
 * 
 * @author Spring Boot Boilerplate
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String error;

    /**
     * Constructor with message and status
     * 
     * @param message Error message
     * @param status HTTP status code
     */
    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.error = status.getReasonPhrase();
    }

    /**
     * Constructor with message, status, and error type
     * 
     * @param message Error message
     * @param status HTTP status code
     * @param error Error type
     */
    public BusinessException(String message, HttpStatus status, String error) {
        super(message);
        this.status = status;
        this.error = error;
    }

    /**
     * Constructor with message, status, error type, and cause
     * 
     * @param message Error message
     * @param status HTTP status code
     * @param error Error type
     * @param cause Original exception
     */
    public BusinessException(String message, HttpStatus status, String error, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.error = error;
    }

    /**
     * Get HTTP status
     * 
     * @return HTTP status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Get error type
     * 
     * @return Error type
     */
    public String getError() {
        return error;
    }
} 