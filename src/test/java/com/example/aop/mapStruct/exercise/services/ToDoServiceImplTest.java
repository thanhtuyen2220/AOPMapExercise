package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.models.ToDo;
import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.repository.ToDoRepository;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceImplTest {
    @Mock
    private static ToDoRepository toDoRepository;

    @Mock
    private static UserRepository userRepository;

    @InjectMocks
    private static ToDoService toDoService = new ToDoServiceImpl();

    private CreateToDoRequest toDoRequest;

    private final String title = "Meetings";
    private final String content = "Meetings with Dr Strange at 5pm";
    private final String authorId = "1";

    private final String Email = "admin@gmail.com";
    private final String fullName = "Admin";

    private final Integer NUM_OF_TODO = 4;

    User mockUser = Mockito.mock(User.class);

    ToDo mockToDo = Mockito.mock(ToDo.class);

    private List<String> idList = new ArrayList<>();

    private final String order = "asc";

    private final String sortField = "content";

    private final Integer page = 0;

    @Before
    public void setup() {
        mockUser.setEmail(Email);
        mockUser.setFullName(fullName);
        mockUser.setId("1");
    }


    @Test
    public void testCreateTodo_GetSuccesfully(){
        toDoRequest = new CreateToDoRequest();
        //toDo.setId(toDoId);
        toDoRequest.setTitle(title);
        toDoRequest.setStatus(CreateToDoRequest.StatusEnum.DONE);
        toDoRequest.setPriority(CreateToDoRequest.PriorityEnum.HIGH);
        toDoRequest.setContent(content);
        toDoRequest.setAuthorId(authorId);
        ToDo toDoInformation = MapStructMapper.INSTANCE.toDoInformation(toDoRequest);

        Mockito.when(userRepository.findUserById(authorId)).thenReturn(mockUser);
        Mockito.when(toDoRepository.save(toDoInformation)).thenReturn(toDoInformation);
        ToDo toDoCreate = toDoService.createTask(toDoRequest);
        Assert.assertEquals(toDoCreate,toDoInformation);
    }

    @Test
    public void testFindAllMustReturnEnoughQuantity() {
        for(Integer i = 0; i < NUM_OF_TODO; i++ ){
            mockToDo.setAuthorId("1");
            mockToDo.setPriority(CreateToDoRequest.PriorityEnum.MEDIUM.toString());
            mockToDo.setContent(RandomStringUtils.random(254));
            mockToDo.setStatus(CreateToDoRequest.StatusEnum.DONE.toString());
            mockToDo.setTitle(RandomStringUtils.random(254));
            idList.add(mockToDo.getId());
            Mockito.when(toDoRepository.save(mockToDo)).thenReturn(mockToDo);
        }
        Mockito.when(toDoRepository.findAll()).thenReturn(Collections.singletonList(mockToDo));
        Assert.assertEquals(java.util.Optional.of(NUM_OF_TODO),Collections.singletonList(mockToDo).size());
    }
    @Test
    public void testGetTaskList(){

    }
}