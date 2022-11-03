package com.example.project.domain.company.presentation;

import com.example.project.domain.company.presentation.dto.request.CreateCompanyRequest;
import com.example.project.domain.company.presentation.dto.request.ParticipateCompanyRequest;
import com.example.project.domain.company.presentation.dto.reseponse.CompanyInfoResponse;
import com.example.project.domain.company.presentation.dto.reseponse.CreateCompanyResponse;
import com.example.project.domain.company.presentation.dto.reseponse.InviteCodeResponse;
import com.example.project.domain.company.presentation.dto.reseponse.ParticipateCompanyResponse;
import com.example.project.domain.company.service.CreateCompanyService;
import com.example.project.domain.company.service.ParticipateCompanyService;
import com.example.project.domain.company.service.QueryInviteCodeService;
import com.example.project.domain.company.service.QueryMyCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companys")
@RestController
public class CompanyController {

    private final CreateCompanyService createCompanyService;
    private final QueryMyCompanyService queryMyCompanyService;
    private final QueryInviteCodeService queryInviteCodeService;
    private final ParticipateCompanyService participateCompanyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CreateCompanyResponse createCompany(@RequestBody @Valid CreateCompanyRequest request){
        return createCompanyService.execute(request);
    }

    @PostMapping("")
    public CompanyInfoResponse queryMyCompany(){
        return queryMyCompanyService.execute();
    }

    @GetMapping("/invite")
    public InviteCodeResponse queryInviteCode(){
        return queryInviteCodeService.execute();
    }

    @GetMapping("/invite")
    public ParticipateCompanyResponse participateCompany(@RequestBody @Valid ParticipateCompanyRequest request){
        return participateCompanyService.execute(request);
    }

}