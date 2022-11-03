package com.example.project.domain.company.service;

import com.example.project.domain.company.domain.Company;
import com.example.project.domain.company.domain.repository.CompanyRepository;
import com.example.project.domain.company.exception.CompanyNotFoundException;
import com.example.project.domain.company.presentation.dto.request.ParticipateCompanyRequest;
import com.example.project.domain.company.presentation.dto.reseponse.ParticipateCompanyResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipateCompanyService {

    private final CompanyRepository companyRepository;
    private final UserFacade userFacade;

    public ParticipateCompanyResponse execute(ParticipateCompanyRequest request) {

        Company company = companyRepository.queryByInviteCode(request.getCode())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        user.participateCompany(company);

        return new ParticipateCompanyResponse(company.getId());
    }
}