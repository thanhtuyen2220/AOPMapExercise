package com.example.aop.mapStruct.exercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyAccountExistedException extends RuntimeException {
    public AlreadyAccountExistedException(String exception) {
        super(exception);
    }
}
