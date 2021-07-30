package com.example.aop.mapStruct.exercise.mappers;
import com.example.aop.mapStruct.exercise.api.model.*;
import com.example.aop.mapStruct.exercise.models.ToDo;
import com.example.aop.mapStruct.exercise.models.User;
import com.example.aop.mapStruct.exercise.models.openFeign.Characters;
import com.example.aop.mapStruct.exercise.models.openFeign.Location;
import com.example.aop.mapStruct.exercise.models.openFeign.Metadata;
import com.example.aop.mapStruct.exercise.models.openFeign.Origin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "priority", source = "request.priority" )
    ToDo toDoInformation(final CreateToDoRequest request);

    @Mapping(target = "id",ignore = true)
    User userInformation(final CreateUserRequest userRequest);

    @Mapping(target="name",source = "user.fullName")
    UserResponseModel userRespnseInfo(final User user);

    TaskResponseModel taskResponseInfo(final ToDo toDo);


    MetadataModel metaDataInfo(final Metadata metadata);

    CharacterModel characterModel(final Character character);

    LocationModel locationModel(final Location location);

    OriginModel originModel (final Origin origin);

    CharacterListResponse characterList(final Characters characters);

    default String mappingPriorityEnum(CreateToDoRequest.PriorityEnum priorityEnum){
        return priorityEnum.toString();
    }


}
