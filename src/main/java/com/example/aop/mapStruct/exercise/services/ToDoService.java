package com.example.aop.mapStruct.exercise.services;


import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.api.model.TaskListResponse;
import com.example.aop.mapStruct.exercise.models.ToDo;
import org.springframework.data.domain.Pageable;

public interface ToDoService {
     ToDo createTask(CreateToDoRequest toDo);
     TaskListResponse getTaskList(Pageable pageable);

}
