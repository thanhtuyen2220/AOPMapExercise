package com.example.aop.mapStruct.exercise.helper;

import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

public enum TaskListOrder {
    ASC {
        @Override
        public Pageable apply(int page, int pageSize, String sortField) {
            sortField = validateSortField(sortField);
            return PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sortField));
        }
    },
    DESC {
        @Override
        public Pageable apply(int page, int pageSize, String sortField) {
            sortField = validateSortField(sortField);
            return PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, sortField));
        }
    };



    public abstract Pageable apply(int page, int pageSize, String sortField);
    private static String validateSortField(String sortField) {
        List<String> validFields = Arrays.asList("authorName", "title","content","priority","status");
        if (!validFields.contains(sortField)) {
            throw new BadRequestException("Invalid sort field.Please try again");
        }
        String result = sortField;
        return result;
    }
}
