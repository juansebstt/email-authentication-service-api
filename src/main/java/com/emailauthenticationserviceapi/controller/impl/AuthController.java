package com.emailauthenticationserviceapi.controller.impl;

import com.emailauthenticationserviceapi.common.dto.LoginRequest;
import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import com.emailauthenticationserviceapi.common.dto.UserRequest;
import com.emailauthenticationserviceapi.controller.AuthApi;
import com.emailauthenticationserviceapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }
}
