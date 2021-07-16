package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Override
    public User createNewUser(CreateUserRequest request) {
        User user = MapStructMapper.INSTANCE.userInformation(request);
        return userRepository.save(user);
    }
}
