package com.example.aop.mapStruct.exercise.services;

import com.example.aop.mapStruct.exercise.api.model.CharacterListResponse;
import com.example.aop.mapStruct.exercise.exceptions.IntegrationException;
import com.example.aop.mapStruct.exercise.mappers.MapStructMapper;
import com.example.aop.mapStruct.exercise.openFeign.client.RickAndMortyClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CharacterServiceImpl implements CharacterService{
    @Autowired
    private RickAndMortyClient client;

    @Override
    public CharacterListResponse getCharacters() {
        try{
            log.info("CharacterServiceImpl.getCharacters - start");
            CharacterListResponse response = MapStructMapper.INSTANCE.characterList(client.getCharacters());
            log.info("CharacterServiceImpl.getCharacters - end");

            return response;
        } catch (FeignException ex) {
            log.error("CharacterServiceImpl.getCharacters - error - {}", ex.getMessage(), ex);
            throw new IntegrationException(ex);
        }
    }
}
