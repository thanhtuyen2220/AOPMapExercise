package com.example.aop.mapStruct.exercise.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public interface ErrorCode {
	int getStatus();
	int getCode();
	String getName();
}