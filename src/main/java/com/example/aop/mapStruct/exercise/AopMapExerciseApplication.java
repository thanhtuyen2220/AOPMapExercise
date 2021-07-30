package com.example.aop.mapStruct.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AopMapExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopMapExerciseApplication.class, args);
	}

}
