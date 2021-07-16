package com.example.aop.mapStruct.exercise.controllers;

import com.example.aop.mapStruct.exercise.services.ToDoService;
import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.api.model.ObjectCreationSuccessResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.Validate;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("ToDo")
public class ToDoController {
    public static final String POST_TODO_INFO_URI = "/postTodoInfo";



    public ToDoService toDoService;



    @RequestMapping(value = POST_TODO_INFO_URI, method = RequestMethod.POST)
    public ResponseEntity<ObjectCreationSuccessResponse> postTodoInfo(@RequestBody final CreateToDoRequest request) {
        Validate.notBlank(request.getContent(), "content must not be empty");
        Validate.notBlank(request.getAuthorId(),"AuthorId must not be empty");
        Validate.notBlank(request.getPriority().toString(),"Priority must not be empty");
        Validate.notBlank(request.getTitle(),"Title must not be empty");
        Validate.notBlank(request.getStatus().toString(),"Status must not be empty");
        toDoService.createTask(request);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setResponseCode(HttpStatus.CREATED.value());
        result.setMessage("Successfully create a new ToDoInfo");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}