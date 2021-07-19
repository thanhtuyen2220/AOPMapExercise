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
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Basic
    @Column(name = "email", unique = true,nullable = false, length = 250)
    private String email;


    @Basic
    @Column(name = "fullname", nullable = false, length = 250)
    private String fullName;

}
