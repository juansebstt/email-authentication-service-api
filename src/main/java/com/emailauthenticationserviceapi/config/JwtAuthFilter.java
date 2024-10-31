package com.emailauthenticationserviceapi.config;

import com.emailauthenticationserviceapi.common.entity.UserModel;
import com.emailauthenticationserviceapi.repository.UserRepository;
import com.emailauthenticationserviceapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional.ofNullable(request.getHeader("Authentication"))
                .filter(header -> !header.isBlank())
                .map(header -> header.substring(7))
                .map(jwtService::extractedUserId)
                .flatMap(userid -> userRepository.findById(Long.valueOf(userid)))
                .ifPresent(userDetails -> {
                    request.setAttribute("X-User-Id", userDetails.getUserId());
                    processAuthentication(request, userDetails);
                });

        filterChain.doFilter(request, response);
    }

    private void processAuthentication(HttpServletRequest request, UserModel userDetails) {
        String jwtToken = request.getHeader("Authentication").substring(7);
        Optional.of(jwtToken)
                .filter(token -> !jwtService.isExpired(token))
                .ifPresent(token -> {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
    }
}