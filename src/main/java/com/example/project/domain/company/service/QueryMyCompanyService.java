package com.example.project.domain.company.service;

import com.example.project.domain.company.domain.Company;
import com.example.project.domain.company.exception.CompanyNotFoundException;
import com.example.project.domain.company.presentation.dto.reseponse.CompanyInfoResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueryMyCompanyService {

    private final UserFacade userFacade;

    public CompanyInfoResponse execute() {

        User user = userFacade.getCurrentUser();
        Company company = user.getCompany();

        if(company == null) throw CompanyNotFoundException.EXCEPTION;

        return CompanyInfoResponse
                .builder()
                .companyId(company.getId())
                .name(company.getName())
                .flexTime(company.getFlexTime())
                .employeesCount(company.getEmployeeList().size())
                .build();
    }
}