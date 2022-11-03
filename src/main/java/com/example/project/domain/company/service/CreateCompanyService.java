package com.example.project.domain.company.service;

import com.example.project.domain.company.domain.Company;
import com.example.project.domain.company.domain.enums.FlexPlace;
import com.example.project.domain.company.domain.repository.CompanyRepository;
import com.example.project.domain.company.presentation.dto.request.CreateCompanyRequest;
import com.example.project.domain.company.presentation.dto.reseponse.CreateCompanyResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.domain.repository.UserRepository;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateCompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public CreateCompanyResponse execute(CreateCompanyRequest request) {

        String uuidCode = UUID.randomUUID().toString();

        Boolean isAcceptHome = request.getFlexPlaceList()
                .stream()
                .filter(o -> o.equals(FlexPlace.HOME)).collect(Collectors.toList()).size() != 0;

        Boolean isAcceptSmartWork = request.getFlexPlaceList()
                .stream()
                .filter(o -> o.equals(FlexPlace.SMART_WORK)).collect(Collectors.toList()).size() != 0;

        Company company = companyRepository.save(Company
                .builder()
                .name(request.getName())
                .flexTime(request.getFlexTime())
                .home(isAcceptHome)
                .smartWork(isAcceptSmartWork)
                .inviteCode(uuidCode.substring(0, 8))
                .build()
        );

        User user = userFacade.getCurrentUser();
        user.participateCompany(company);
        userRepository.save(user);

        return new CreateCompanyResponse(company.getId());
    }
}