package com.example.project.domain.company.service;

import com.example.project.domain.company.domain.Company;
import com.example.project.domain.company.domain.enums.FlexPlace;
import com.example.project.domain.company.domain.repository.CompanyRepository;
import com.example.project.domain.company.presentation.dto.request.CreateCompanyRequest;
import com.example.project.domain.company.presentation.dto.reseponse.CreateCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCompanyService {

    private final CompanyRepository companyRepository;

    public CreateCompanyResponse execute(CreateCompanyRequest request) {

        String uuidCode = UUID.randomUUID().toString();

        Boolean isAcceptHome = request.getFlexPlaceList()
                .stream()
                .filter(o -> o.equals(FlexPlace.HOME)).toList().size() != 0;

        Boolean isAcceptSmartWork = request.getFlexPlaceList()
                .stream()
                .filter(o -> o.equals(FlexPlace.SMART_WORK)).toList().size() != 0;

        Company company = companyRepository.save(Company
                .builder()
                .name(request.getName())
                .flexTime(request.getFlexTime())
                .home(isAcceptHome)
                .smartWork(isAcceptSmartWork)
                .inviteCode(uuidCode.substring(0, 8))
                .build()
        );

        return new CreateCompanyResponse(company.getId());
    }
}