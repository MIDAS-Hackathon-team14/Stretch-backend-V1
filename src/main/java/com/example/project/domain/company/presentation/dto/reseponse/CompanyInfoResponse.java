package com.example.project.domain.company.presentation.dto.reseponse;

import com.example.project.domain.company.domain.enums.FlexTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyInfoResponse {

    private Long companyId;
    private String name;
    private FlexTime flexTime;
    private Integer employeesCount;

}