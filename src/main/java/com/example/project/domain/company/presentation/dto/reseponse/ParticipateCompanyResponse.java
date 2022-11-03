package com.example.project.domain.company.presentation.dto.reseponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ParticipateCompanyResponse {
    private final Long companyId;
}