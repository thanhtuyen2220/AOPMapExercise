package com.example.aop.mapStruct.exercise.repository;

import com.example.aop.mapStruct.exercise.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo, String> {

}
