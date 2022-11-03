package com.example.project.domain.company.presentation.dto.request;

import com.example.project.domain.company.domain.enums.FlexPlace;
import com.example.project.domain.company.domain.enums.FlexTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateCompanyRequest {

    private String name;
    private FlexTime flexTime;
    private List<FlexPlace> flexPlaceList;
}