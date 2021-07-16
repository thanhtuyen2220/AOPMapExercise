package com.example.aop.mapStruct.exercise.mappers;
import com.example.aop.mapStruct.exercise.api.model.CreateToDoRequest;
import com.example.aop.mapStruct.exercise.api.model.CreateUserRequest;
import com.example.aop.mapStruct.exercise.models.ToDo;
import com.example.aop.mapStruct.exercise.models.User;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "priority", source = "request.priority" )
    @Mapping(target = "user" ,source = "request.authorId")
    ToDo toDoInformation(final CreateToDoRequest request);

    @Mapping(target = "id",ignore = true)
    User userInformation(final CreateUserRequest userRequest);

    default String mappingPriorityEnum(CreateToDoRequest.PriorityEnum priorityEnum){
        return priorityEnum.toString();
    }


}
