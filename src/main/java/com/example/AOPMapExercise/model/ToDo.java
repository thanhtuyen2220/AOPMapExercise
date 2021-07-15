package com.example.AOPMapExercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ToDo")
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

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "todos"
    )
    private Set<User> authors = new HashSet<>();
}
