package com.example.project.domain.company.presentation.dto.reseponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyInfoResponse {

    private Long companyId;
    private String name;
    private String flexTime;
    private Integer employeesCount;

}