package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.UserListResponse;
import com.example.aop.mapStruct.exercise.api.model.UserResponseModel;
import com.example.aop.mapStruct.exercise.exceptions.AlreadyAccountExistedException;
import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import com.example.aop.mapStruct.exercise.exceptions.InvalidDataException;
import com.example.aop.mapStruct.exercise.exceptions.ResourceNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    private final static int pageSize = 10;


    @Override
    @Transactional(rollbackFor = Exception.class, noRollbackFor = ResourceNotFoundException.class)
    public User createNewUser(CreateUserRequest request) {
        if(request == null){
            throw new BadRequestException("Not valid data.Field must not be null");
        }
        if(!ValidationHelper.validate(request.getEmail()) || request.getEmail() == null ){
            throw new InvalidDataException("Not valid data.Please check again");
        }

        if(userRepository.findUserByEmail(request.getEmail())!=null){
            throw new AlreadyAccountExistedException("This account is existed,please try again.");
        }
        User user = MapStructMapper.INSTANCE.userInformation(request);
        return userRepository.save(user);

    }


    @Override
    public UserListResponse getUserList(Pageable requestedPage) {
        if(requestedPage.getPageNumber() < 0){
            throw new BadRequestException("Not valid data.Make sure page number greater than 0");
        }
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
