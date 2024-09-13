package com.example.apiMagic.apiMagic.dto;

import com.example.apiMagic.apiMagic.service.RoleNameDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record CreateUserDto(
        String username,
        String email,
        String password,
        @JsonDeserialize(using = RoleNameDeserializer.class) RoleName role
) {
}
