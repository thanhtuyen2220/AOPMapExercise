package com.example.aop.mapStruct.exercise.controllers;

import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.services.UserService;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private UserController userController;

    @Autowired
    public UserService userService;
    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail(RandomStringUtils.random(25) + "@gmail.com");
        user.setFullName(RandomStringUtils.random(25));
        RestAssured
                .given()
                .body(user)
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/user/postNewUsers")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testGetUserList(){
        RestAssured
                .given()
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .queryParam("order","asc")
                .queryParam("field","email")
                .queryParam("page","0")
                .get("user/getUserList")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
