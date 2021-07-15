package com.example.AOPMapExercise.Service;


import com.example.AOPMapExercise.Mapper.MapStructMapper;
import com.example.AOPMapExercise.Repository.ToDoRepository;
import com.example.AOPMapExercise.api.model.CreateToDoRequest;
import com.example.AOPMapExercise.model.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    public ToDoRepository toDoRepository;

    @Override
    public ToDo createTask(CreateToDoRequest toDoRequest) {
        //log.info("\nRequest"+toDoRequest.toString());
        ToDo toDoInformation = MapStructMapper.INSTANCE.toDoInformation(toDoRequest);
        ToDo postToDoInformation = toDoRepository.save(toDoInformation);
        return postToDoInformation;
    }
}
