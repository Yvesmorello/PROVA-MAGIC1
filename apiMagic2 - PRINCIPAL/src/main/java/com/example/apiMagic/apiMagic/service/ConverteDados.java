package com.example.apiMagic.apiMagic.service;

import com.example.apiMagic.apiMagic.dto.CardsResponse;
import com.example.apiMagic.apiMagic.dto.CardsResponseCommander;
import com.example.apiMagic.apiMagic.dto.DadosCards;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public  CardsResponse obterDados(String json, Class<CardsResponse> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public CardsResponseCommander obterDadosCommander(String json, Class<CardsResponseCommander> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
