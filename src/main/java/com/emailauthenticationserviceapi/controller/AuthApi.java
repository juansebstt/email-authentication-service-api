package com.emailauthenticationserviceapi.controller;

import com.emailauthenticationserviceapi.common.constant.ApiPathConstants;
import com.emailauthenticationserviceapi.common.dto.LoginRequest;
import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import com.emailauthenticationserviceapi.common.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> createUser(
            @RequestBody @Valid UserRequest userRequest
    );

    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse> loginUser(
            @RequestBody @Valid LoginRequest loginRequest
    );
}
