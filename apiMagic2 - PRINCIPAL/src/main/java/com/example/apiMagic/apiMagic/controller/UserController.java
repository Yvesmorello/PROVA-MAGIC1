package com.example.apiMagic.apiMagic.controller;

import com.example.apiMagic.apiMagic.dto.CreateUserDto;
import com.example.apiMagic.apiMagic.dto.LoginUserDto;
import com.example.apiMagic.apiMagic.dto.RecoveryJwtTokenDto;
import com.example.apiMagic.apiMagic.dto.UserResponseDto;
import com.example.apiMagic.apiMagic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        UserResponseDto userResponse = userService.createUser(createUserDto);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}