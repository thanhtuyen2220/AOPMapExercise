package com.example.aop.mapStruct.exercise.services;


import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.models.ToDo;

public interface ToDoService {
     ToDo createTask(CreateToDoRequest toDo);
}