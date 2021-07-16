package com.example.aop.mapStruct.exercise.repository;

import com.example.aop.mapStruct.exercise.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, String> {}
