package com.example.aop.mapStruct.exercise.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "todo")
@Data
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Basic
    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @Basic
    @Column(name = "content",nullable = false, length = 250)
    private String content;

    @Basic
    @Column(name = "priority",nullable = false, length = 250)
    private String priority;

    @Basic
    @Column(name = "status",nullable = false,columnDefinition = "false")
    private Boolean status;

    @Basic
    @Column(name= "author_id",nullable = false,length = 255)
    private String authorId;
}
