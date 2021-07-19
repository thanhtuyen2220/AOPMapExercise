package com.example.aop.mapStruct.exercise.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ToDoModelTest {

    private final String title = "Meetings";
    private final String content = "Meetings with Dr Strange at 5pm";
    private final String status = "DONE";
    private final String authorId = "1";
    private final String priority = "Medium";
    @Test
    void testConstructorToDo() {
        ToDo toDo = initToDo();
        assertEquals(title,toDo.getTitle());
        assertEquals(content,toDo.getContent());
        assertEquals(authorId,toDo.getAuthorId());
        assertEquals(status,toDo.getStatus());
        assertEquals(priority,toDo.getPriority());
    }

    private ToDo initToDo(){
        ToDo toDo = new ToDo();
        toDo.setAuthorId(authorId);
        toDo.setContent(content);
        toDo.setPriority(priority);
        toDo.setStatus(status);
        toDo.setTitle(title);
        return toDo;
    }
}
