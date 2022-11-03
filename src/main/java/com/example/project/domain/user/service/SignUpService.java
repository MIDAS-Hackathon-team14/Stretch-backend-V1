package com.example.project.domain.user.service;

import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.domain.enums.Authority;
import com.example.project.domain.user.domain.repository.UserRepository;
import com.example.project.domain.user.exception.UserAlreadyExistException;
import com.example.project.domain.user.facade.UserFacade;
import com.example.project.domain.user.presentation.dto.request.SignUpRequest;
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
public class SignUpService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(SignUpRequest request) {

        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();

        if(userFacade.emailIsExist(email)) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(User
                .builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.USER)
                .build()
        );

        String accessToken = jwtTokenProvider.createAccessToken(email);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .build();
    }
}