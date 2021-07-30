package com.example.aop.mapStruct.exercise.openFeign.client;

import com.example.aop.mapStruct.exercise.hystrix.RickAndMortyFallBack;
import com.example.aop.mapStruct.exercise.models.openFeign.Characters;
import openFeign.config.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "rickAndMortyClient", url = "${rickAndMorty.url}", configuration = ClientConfiguration.class,fallback = RickAndMortyFallBack.class)
public interface RickAndMortyClient {
    @RequestMapping(method = RequestMethod.GET, value = "/character")
    Characters getCharacters();

    @RequestMapping(method = RequestMethod.GET, value = "/character/{id}")
    Character getCharacter(@PathVariable("id") Long  id);

}
