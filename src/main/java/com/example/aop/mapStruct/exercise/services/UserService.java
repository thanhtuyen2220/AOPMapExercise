package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.api.model.UserListResponse;
import com.example.aop.mapStruct.exercise.models.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createNewUser(CreateUserRequest request);
    UserListResponse getUserList(Pageable requestedPage);
}
