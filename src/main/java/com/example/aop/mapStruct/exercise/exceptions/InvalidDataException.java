package com.example.aop.mapStruct.exercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String exception) {
        super(exception);
    }
}
