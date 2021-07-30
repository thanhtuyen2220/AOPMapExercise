package com.example.aop.mapStruct.exercise.hystrix;

import com.example.aop.mapStruct.exercise.exceptions.BadRequestException;
import com.example.aop.mapStruct.exercise.models.openFeign.Character;
import com.example.aop.mapStruct.exercise.models.openFeign.Characters;
import com.example.aop.mapStruct.exercise.models.openFeign.Metadata;
import com.example.aop.mapStruct.exercise.openFeign.client.RickAndMortyClient;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyFallBack implements  FallbackFactory<RickAndMortyClient> {


    @Override
    public RickAndMortyClient create(Throwable cause) {
        return new RickAndMortyClient() {
            @Override
            public Characters getCharacters() {
                // Todo: fix it later
                // fallback cannot throw exception
                throw new BadRequestException("fallback because error");
            }

            @Override
            public java.lang.Character getCharacter(Long id) {
                return null;
            }
        };
    }
}
