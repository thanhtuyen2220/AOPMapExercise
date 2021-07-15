package com.example.AOPMapExercise.Controller;

import com.example.AOPMapExercise.Service.UserService;
import com.example.AOPMapExercise.api.model.CreateToDoRequest;
import com.example.AOPMapExercise.api.model.CreateUserRequest;
import com.example.AOPMapExercise.api.model.ObjectCreationSuccessResponse;
import com.example.AOPMapExercise.model.ToDo;
import com.example.AOPMapExercise.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {
    public static final String POST_USER_INFO_URI = "/postNewUsers";

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
}
