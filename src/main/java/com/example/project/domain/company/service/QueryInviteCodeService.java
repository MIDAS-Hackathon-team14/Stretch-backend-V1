package com.example.project.domain.company.service;

import com.example.project.domain.company.presentation.dto.reseponse.InviteCodeResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryInviteCodeService {

    private final UserFacade userFacade;

    public InviteCodeResponse execute() {

        User user = userFacade.getCurrentUser();

        return new InviteCodeResponse(user.getCompany().getInviteCode());
    }

}