package com.example.aop.mapStruct.exercise.controllers;

import com.example.aop.mapStruct.exercise.api.model.*;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import com.example.aop.mapStruct.exercise.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {
    public static final String POST_USER_INFO_URI = "/postNewUsers";

    public static final String GET_USER_INFO_URI = "/getUserList";

    public UserService userService;

    @RequestMapping(value = POST_USER_INFO_URI, method = RequestMethod.POST)
    public ResponseEntity<ObjectCreationSuccessResponse> postNewUsersInfo(@RequestBody final CreateUserRequest request) {
        Validate.notBlank(request.getEmail(), "Email must not be empty");
        Validate.notBlank(request.getFullName(),"Fullname must not be empty");
        userService.createNewUser(request);
        ObjectCreationSuccessResponse result = new ObjectCreationSuccessResponse();
        result.setResponseCode(HttpStatus.CREATED.value());
        result.setMessage("Successfully");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = GET_USER_INFO_URI, method = RequestMethod.GET)
    public ResponseEntity<UserListResponse> getUserInfo(@RequestParam final String order, String field,Integer page){
        Validate.notBlank(order,"order field must not be empty");
        Validate.notBlank(field, "sort field must not be empty");
        Validate.notBlank(page.toString(),"page number must not be empty");
        UserListResponse userList = userService.getUserList(order,field,page);
        return ResponseEntity.ok(userList);
    }
}
