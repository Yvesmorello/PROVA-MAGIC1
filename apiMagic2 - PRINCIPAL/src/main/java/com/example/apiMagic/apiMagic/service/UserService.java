package com.example.apiMagic.apiMagic.service;

import com.example.apiMagic.apiMagic.auth.JwtTokenService;
import com.example.apiMagic.apiMagic.auth.SecurityConfiguration;
import com.example.apiMagic.apiMagic.dto.CreateUserDto;
import com.example.apiMagic.apiMagic.dto.LoginUserDto;
import com.example.apiMagic.apiMagic.dto.RecoveryJwtTokenDto;
import com.example.apiMagic.apiMagic.dto.UserResponseDto;
import com.example.apiMagic.apiMagic.model.Role;
import com.example.apiMagic.apiMagic.model.User;
import com.example.apiMagic.apiMagic.model.UserDetailsImpl;
import com.example.apiMagic.apiMagic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

//    public void createUser(CreateUserDto createUserDto) {
//
//        User newUser = User.builder()
//                .email(createUserDto.email())
//                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
//                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
//                .build();
//        userRepository.save(newUser);
//    }
public UserResponseDto createUser(CreateUserDto createUserDto) {
    User newUser = User.builder()
            .username(createUserDto.username())
            .email(createUserDto.email())
            .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
            .roles(List.of(Role.builder().name(createUserDto.role()).build()))
            .build();
    userRepository.save(newUser);

    // Retorna UserResponseDto com detalhes do usuário criado e a senha criptografada
    return new UserResponseDto(newUser.getUsername(), newUser.getEmail(), newUser.getPassword());
}
}
