package com.example.AOPMapExercise.Mapper;
import com.example.AOPMapExercise.api.model.CreateToDoRequest;
import com.example.AOPMapExercise.api.model.CreateUserRequest;
import com.example.AOPMapExercise.model.ToDo;
import com.example.AOPMapExercise.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(target = "priority", source = "request.priority" )
    ToDo toDoInformation(final CreateToDoRequest request);

    User userInformation(final CreateUserRequest userRequest);
}
