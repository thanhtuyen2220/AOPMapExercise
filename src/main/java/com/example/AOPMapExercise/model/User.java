package com.example.AOPMapExercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Basic
    @Column(name = "email", nullable = false, length = 250)
    @Email
    private String email;


    @Basic
    @Column(name = "fullname", nullable = false, length = 250)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_toDo",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
    private Set<ToDo> toDos = new HashSet<>();
}
