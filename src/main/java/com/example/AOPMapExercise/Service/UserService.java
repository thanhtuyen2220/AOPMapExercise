package com.example.AOPMapExercise.Service;

import com.example.AOPMapExercise.api.model.CreateUserRequest;
import com.example.AOPMapExercise.model.User;

public interface UserService {
    User createNewUser(CreateUserRequest request);
}
