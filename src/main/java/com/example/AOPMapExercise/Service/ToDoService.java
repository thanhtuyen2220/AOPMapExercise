package com.example.AOPMapExercise.Service;


import com.example.AOPMapExercise.api.model.CreateToDoRequest;
import com.example.AOPMapExercise.model.ToDo;

public interface ToDoService {
     ToDo createTask(CreateToDoRequest toDo);
}
