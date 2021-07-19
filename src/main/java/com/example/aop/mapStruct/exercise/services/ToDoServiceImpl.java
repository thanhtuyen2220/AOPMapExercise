package com.example.aop.mapStruct.exercise.services;


import com.example.aop.mapStruct.exercise.api.model.*;
import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import com.example.aop.mapStruct.exercise.helper.TaskListOrder;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.repository.ToDoRepository;
import com.example.aop.mapStruct.exercise.models.ToDo;
import com.example.aop.mapStruct.exercise.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    public ToDoRepository toDoRepository;

    @Autowired
    public UserRepository userRepository;

    private final static int pageSize = 10;

    @Override
    public ToDo createTask(CreateToDoRequest toDoRequest) {
        //log.info("\nRequest"+toDoRequest.toString());
        ToDo toDoInformation = MapStructMapper.INSTANCE.toDoInformation(toDoRequest);
            return toDoRepository.save(toDoInformation);

    }

    @Override
    public TaskListResponse getTaskList(String order, String sortField, int page) {
        if(page < 0){
            //TODO 5: Add exception after configuring
            throw new BadRequestException("Invalid data.Make sure page number is greater than 0");
        }
        String orderByField = order.toUpperCase();
        Pageable requestedPage = TaskListOrder.valueOf(orderByField)
                .apply(page, pageSize, sortField);
        Page<ToDo> taskList;
        taskList = toDoRepository.findAll(requestedPage);
        return buildTaskList(taskList);
    }
    private TaskListResponse buildTaskList(Page<ToDo> toDoLists) {
        TaskListResponse taskListResponse = new TaskListResponse();
        toDoLists.stream().forEach(toDo -> {
            User author = userRepository.findUserById(toDo.getAuthorId());
            TaskResponseModel taskResponseModel =
                    MapStructMapper.INSTANCE.taskResponseInfo(toDo);
            taskResponseModel.authorName(author.getFullName());
            taskListResponse.addTasksItem(taskResponseModel);
        });
        taskListResponse.setCurrentPage(toDoLists.getNumber());
        taskListResponse.setTotalPage(toDoLists.getTotalPages());

        return taskListResponse;
    }
}
