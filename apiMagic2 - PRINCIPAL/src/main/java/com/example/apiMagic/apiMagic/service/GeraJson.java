package com.example.apiMagic.apiMagic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class GeraJson {

    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> void salvarEmJson(List<T> item, String fileName) throws IOException {
        objectMapper.writeValue(Paths.get(fileName).toFile(), item);
    }
}
