package com.emailauthenticationserviceapi.service.impl;

import com.emailauthenticationserviceapi.common.dto.LoginRequest;
import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import com.emailauthenticationserviceapi.common.dto.UserRequest;
import com.emailauthenticationserviceapi.common.entity.UserModel;
import com.emailauthenticationserviceapi.repository.UserRepository;
import com.emailauthenticationserviceapi.service.AuthService;
import com.emailauthenticationserviceapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {

        return Optional.of(userRequest)
                .map(this::toMapEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    private UserModel toMapEntity(UserRequest userRequest) {

        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .role("USER")
                .build();
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {

        return userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                .map(UserModel::getUserId)
                .map(jwtService::generateToken)
                .orElseThrow(() -> new RuntimeException("Error login user"));

    }
}
