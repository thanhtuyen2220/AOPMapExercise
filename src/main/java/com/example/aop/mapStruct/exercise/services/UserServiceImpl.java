package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.UserListResponse;
import com.example.aop.mapStruct.exercise.api.model.UserResponseModel;
import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import com.example.aop.mapStruct.exercise.helper.UserListOrder;
import com.example.aop.mapStruct.exercise.helper.ValidationHelper;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    private final static int pageSize = 10;


    @Override
    public User createNewUser(CreateUserRequest request) {
        if(request != null){
            if(ValidationHelper.validate(request.getEmail()) || request.getFullName()!= null) {
                User user = MapStructMapper.INSTANCE.userInformation(request);
                return userRepository.save(user);
            }
            else {
                throw new BadRequestException("Not valid data");
            }
        }
        else throw new BadRequestException("Not valid data");

    }

    @Override
    public User findUserById(String Id) {
        return  userRepository.findUserById(Id);
    }

    @Override
    public UserListResponse getUserList(String order, String sortField, int page) {
        if(page < 0){
            throw new BadRequestException("Not valid data.Make sure page number greater than 0");
        }
        String orderByField = order.toUpperCase();
        Pageable requestedPage = UserListOrder.valueOf(orderByField)
                .apply(page, pageSize, sortField);
        Page<User> userList;
        userList = userRepository.findAll(requestedPage);
        return buildUserList(userList);
    }
    private UserListResponse buildUserList(Page<User> userLists) {
        UserListResponse userListResponse = new UserListResponse();
        userLists.stream().forEach(user -> {
            UserResponseModel userResponseModel =
                MapStructMapper.INSTANCE.userRespnseInfo(user);
                userListResponse.addUsersItem(userResponseModel);
        });
        userListResponse.setCurrentPage(userLists.getNumber());
        userListResponse.setTotalPage(userLists.getTotalPages());

        return userListResponse;
    }
}
