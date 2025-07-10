package com.example.boilerplate.exception;

public class CustomError extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final String message;

    public CustomError(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
} 