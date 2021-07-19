package com.example.aop.mapStruct.exercise.helper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

public enum UserListOrder {
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
        List<String> validFields = Arrays.asList("email", "fullName");
        if (!validFields.contains(sortField)) {
            // TODO 1: add exception after configuring
            //throw new BadRequestException("invalid sort field");

        }
        String result = sortField;
        return result;
    }
}
