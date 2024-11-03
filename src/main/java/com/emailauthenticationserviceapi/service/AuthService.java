package com.emailauthenticationserviceapi.service;

import com.emailauthenticationserviceapi.common.dto.LoginRequest;
import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import com.emailauthenticationserviceapi.common.dto.UserRequest;

public interface AuthService {

    TokenResponse createUser(UserRequest userRequest);

    TokenResponse loginUser(LoginRequest loginRequest);

}
