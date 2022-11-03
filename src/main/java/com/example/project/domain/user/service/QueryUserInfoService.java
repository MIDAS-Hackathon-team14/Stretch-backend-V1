package com.example.project.domain.user.service;

import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import com.example.project.domain.user.presentation.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserInfoResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);

        return UserInfoResponse
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}