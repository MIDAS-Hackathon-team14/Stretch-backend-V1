package com.example.project.domain.user.service;

import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import com.example.project.domain.user.presentation.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class QueryMyInfoService {

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public UserInfoResponse execute(){

        User user = userFacade.getCurrentUser();

        return UserInfoResponse
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}