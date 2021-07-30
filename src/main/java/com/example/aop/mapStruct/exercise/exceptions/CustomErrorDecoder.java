package com.example.aop.mapStruct.exercise.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 400:
                return new BadRequestException("Bad request.Please try again");
            case 404:
                return new NotFoundDataException("Not found data.Please try again");
            default:
                return new Exception("Generic error");
        }
    }
}
