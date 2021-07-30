package com.example.aop.mapStruct.exercise.controllers;

import com.example.aop.mapStruct.exercise.api.model.CharacterListResponse;
import com.example.aop.mapStruct.exercise.models.openFeign.Character;
import com.example.aop.mapStruct.exercise.models.openFeign.Characters;
import com.example.aop.mapStruct.exercise.services.CharacterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("Character")
public class CharacterController {

    public static final String GET_CHARACTER_INFO_URI = "/getCharacterInfo";

    private CharacterService characterService;

    @RequestMapping(value = GET_CHARACTER_INFO_URI, method = RequestMethod.GET)
    public ResponseEntity<CharacterListResponse> getCharacters(){
        CharacterListResponse characterListResponse = characterService.getCharacters();
        return ResponseEntity.ok(characterListResponse);
    }



}
