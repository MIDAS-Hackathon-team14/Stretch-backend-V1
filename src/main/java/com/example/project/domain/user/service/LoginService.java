package com.example.project.domain.user.service;

import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.exception.UserNotFoundException;
import com.example.project.domain.user.facade.UserFacade;
import com.example.project.domain.user.presentation.dto.request.LoginRequest;
import com.example.project.domain.user.presentation.dto.response.TokenResponse;
import com.example.project.global.security.jwt.JwtProperties;
import com.example.project.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserFacade userFacade;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(LoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        User user = userFacade.getUserByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw UserNotFoundException.EXCEPTION;

        String accessToken = jwtTokenProvider.createAccessToken(email);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .build();
    }
}