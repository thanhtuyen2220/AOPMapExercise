package com.example.aop.mapStruct.exercise.controllers;

import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.services.ToDoService;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private ToDoController toDoController;

    @Autowired
    public ToDoService toDoService;


    @Test
    public void createTodo_getSuccessfully(){
        CreateToDoRequest toDo = new CreateToDoRequest();
        toDo.setContent(RandomStringUtils.random(254));
        toDo.setAuthorId("1");
        toDo.setPriority(CreateToDoRequest.PriorityEnum.MEDIUM);
        toDo.setTitle(RandomStringUtils.random(25));
        toDo.setStatus(CreateToDoRequest.StatusEnum.DONE);
        RestAssured
                .given()
                .body(toDo)
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/ToDo/postTodoInfo")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void testCreateTask_givenMissingData_shouldGetException(){
        CreateToDoRequest toDo = new CreateToDoRequest();
        toDo.setContent(RandomStringUtils.random(254));
        toDo.setAuthorId(null);
        toDo.setPriority(CreateToDoRequest.PriorityEnum.MEDIUM);
        toDo.setTitle(RandomStringUtils.random(25));
        toDo.setStatus(CreateToDoRequest.StatusEnum.DONE);
        RestAssured
                .given()
                .body(toDo)
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/ToDo/postTodoInfo")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(500);
    }
    @Test
    public void testCreateTask_givenWrongData_shouldBadRequestException(){
        CreateToDoRequest toDo = new CreateToDoRequest();
        toDo.setContent(RandomStringUtils.random(254));
        toDo.setAuthorId("3");
        toDo.setPriority(CreateToDoRequest.PriorityEnum.MEDIUM);
        toDo.setTitle(RandomStringUtils.random(25));
        toDo.setStatus(CreateToDoRequest.StatusEnum.DONE);
        RestAssured
                .given()
                .body(toDo)
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/ToDo/postTodoInfo")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(404);
    }
    @Test
    public void testGetToDoList(){
        RestAssured
                .given()
                .port(port)
                .urlEncodingEnabled(false)
                .header("Content-Type", "application/json")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .queryParam("order","asc")
                .queryParam("field","content")
                .queryParam("page","0")
                .get("ToDo/getTodoInfo")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

}
