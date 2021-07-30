package com.example.aop.mapStruct.exercise.models.openFeign;

import lombok.Data;

import java.util.List;
@Data
public class Characters {
    private Metadata info;
    private List<Character> results;

}
