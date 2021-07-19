package com.example.aop.mapStruct.exercise.exceptions;

import lombok.Getter;

import java.util.Date;

public class ExceptionResponse {
    @Getter
    private Date timestamp;
    @Getter
    private String errorMessage;
    @Getter
    private String details;
    @Getter
    private Integer errorCode;

    public ExceptionResponse(Date timestamp, String errorMessage, String details, Integer errorCode) {
        super();
        this.timestamp = timestamp;
        this.errorMessage = errorMessage;
        this.details = details;
        this.errorCode = errorCode;
    }
}
