package com.example.aop.mapStruct.exercise.services;
import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.api.model.UserListResponse;
import com.example.aop.mapStruct.exercise.api.model.UserResponseModel;
import com.example.aop.mapStruct.exercise.exceptions.AlreadyAccountExistedException;
import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import com.example.aop.mapStruct.exercise.exceptions.InvalidDataException;
import com.example.aop.mapStruct.exercise.helper.UserListOrder;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private static UserRepository userRepository;

    @InjectMocks
    private static UserService userService = new UserServiceImpl();

    User mockUser = Mockito.mock(User.class);

    private final String Email = "admin@gmail.com";
    private final String fullName = "Admin";

    @Before
    public void setup() {
        mockUser.setEmail(Email);
        mockUser.setFullName(fullName);
        mockUser.setId("1");
    }

    private final String order = "asc";

    private final Integer page = 0;

    private final String field = "email";

    private final Integer pageSize = 10;

    @Test
    public void testCreateUser_getSuccessfully(){
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setFullName("Admin02");
        userRequest.setEmail("Admin02@gmail.com");

        User userInformation = MapStructMapper.INSTANCE.userInformation(userRequest);
        Mockito.when(userRepository.save(userInformation)).thenReturn(userInformation);
        User userCreate = userService.createNewUser(userRequest);
        Assert.assertEquals(userInformation,userCreate);
    }

    @Test(expected = AlreadyAccountExistedException.class)
    public void testCreateUser_whenGiveAlreadyEmailRequest_getAlreadyAccountExistedException(){
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setEmail(Email);
        userRequest.setFullName("admin01");

        User userInformation = MapStructMapper.INSTANCE.userInformation(userRequest);
        Mockito.when(userRepository.save(userInformation)).thenThrow(AlreadyAccountExistedException.class);
        userService.createNewUser(userRequest);
    }

    @Test(expected = InvalidDataException.class)
    public void testCreateUser_whenGiveWrongTypeOfEmail_getInvalidDataException(){
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setEmail("admin01");
        userRequest.setFullName("admin01");

        User userInformation = MapStructMapper.INSTANCE.userInformation(userRequest);
        Mockito.when(userRepository.save(userInformation)).thenThrow(InvalidDataException.class);
        userService.createNewUser(userRequest);
    }


}
