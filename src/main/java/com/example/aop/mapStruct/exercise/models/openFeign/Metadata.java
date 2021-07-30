package com.example.aop.mapStruct.exercise.models.openFeign;

import lombok.Data;

@Data
public class Metadata {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
