package com.example.aop.mapStruct.exercise.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserModelTest {

    private final String Email = "admin@gmail.com";
    private final String fullName = "Admin";

    @Test
    void testConstructorUser() {
        User user = initUser();
        assertEquals(Email,user.getEmail());
        assertEquals(fullName,user.getFullName());
    }

    private User initUser(){
        User user = new User();
        user.setEmail(Email);
        user.setFullName(fullName);
        return user;
    }
}
