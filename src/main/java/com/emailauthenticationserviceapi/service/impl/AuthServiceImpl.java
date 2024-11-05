package com.emailauthenticationserviceapi.service.impl;

import com.emailauthenticationserviceapi.common.dto.LoginRequest;
import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import com.emailauthenticationserviceapi.common.dto.UserRequest;
import com.emailauthenticationserviceapi.repository.UserRepository;
import com.emailauthenticationserviceapi.service.AuthService;
import com.emailauthenticationserviceapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return null;
    }
}
