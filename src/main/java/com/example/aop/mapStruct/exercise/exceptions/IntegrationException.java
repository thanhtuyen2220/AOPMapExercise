package com.example.aop.mapStruct.exercise.exceptions;

public class IntegrationException extends RuntimeException {

    public IntegrationException() {
        super();
    }

    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegrationException(String message) {
        super(message);
    }

    public IntegrationException(Throwable cause) {
        super(cause);
    }

}
