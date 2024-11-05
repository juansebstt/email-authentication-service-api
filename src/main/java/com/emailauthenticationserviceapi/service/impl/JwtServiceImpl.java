package com.emailauthenticationserviceapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl {

    private final String secretToken;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {

        this.secretToken = secretToken;
    }

    

}
