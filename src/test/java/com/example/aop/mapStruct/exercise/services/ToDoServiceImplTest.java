package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.models.ToDo;
import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.repository.ToDoRepository;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

    User mockUser = Mockito.mock(User.class);

    @Before
    public void setup() {
        mockUser.setEmail(Email);
        mockUser.setFullName(fullName);
        mockUser.setId("1");
    }


    @Test
    public void testCreateTodo(){
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
}
