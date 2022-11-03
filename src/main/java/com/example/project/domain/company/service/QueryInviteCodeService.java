package com.example.project.domain.company.service;

import com.example.project.domain.company.presentation.dto.reseponse.InviteCodeResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryInviteCodeService {

    private final UserFacade userFacade;

    @Transactional
    public InviteCodeResponse execute() {

        User user = userFacade.getCurrentUser();

        return new InviteCodeResponse(user.getCompany().getInviteCode());
    }

}