package com.example.aop.mapStruct.exercise.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage() + "not found",
                webRequest.getDescription(false), 2404);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
