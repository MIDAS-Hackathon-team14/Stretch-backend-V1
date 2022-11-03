package com.example.project.domain.admin.service;

import com.example.project.domain.admin.presentation.dto.request.ModifyUserInfoRequest;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.domain.repository.UserRepository;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyUserInfoService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    public void execute(Long userId, ModifyUserInfoRequest request) {

        User user = userFacade.getUserById(userId);
        user.modifyInfo(request.getName(), request.getEmail());

    }
}