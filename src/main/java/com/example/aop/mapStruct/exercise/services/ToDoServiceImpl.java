package com.example.aop.mapStruct.exercise.services;


import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.repository.ToDoRepository;
import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.models.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    public ToDoRepository toDoRepository;

    @Override
    public ToDo createTask(CreateToDoRequest toDoRequest) {
        //log.info("\nRequest"+toDoRequest.toString());
        ToDo toDoInformation = MapStructMapper.INSTANCE.toDoInformation(toDoRequest);
        ToDo postToDoInformation = toDoRepository.save(toDoInformation);
        return postToDoInformation;
    }
}
