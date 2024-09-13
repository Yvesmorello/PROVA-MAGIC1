package com.example.apiMagic.apiMagic.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.example.apiMagic.apiMagic.dto.RoleName;

import java.io.IOException;

public class RoleNameDeserializer extends JsonDeserializer<RoleName> {

    @Override
    public RoleName deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String role = p.getText().toUpperCase();
        try {
            return RoleName.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new IOException("Valor inválido para o enum RoleName: " + role, e);
        }
    }
}
