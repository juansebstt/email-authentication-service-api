package com.emailauthenticationserviceapi.service;

import com.emailauthenticationserviceapi.common.dto.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {

    TokenResponse generateToken(Long userId);

    Claims getClaims(String token);

    boolean isExpired(String token);

    Integer extractedUserId(String token);


}
