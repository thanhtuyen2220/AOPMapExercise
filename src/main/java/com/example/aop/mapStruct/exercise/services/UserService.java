package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.api.model.UserListResponse;
import com.example.aop.mapStruct.exercise.models.User;

public interface UserService {
    User createNewUser(CreateUserRequest request);
    User findUserById(String Id);
    UserListResponse getUserList(String order, String sortField, int page);
}
