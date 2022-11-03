package com.example.project.domain.company.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlexPlace {
    COMPANY("회사"),
    SMART_WORK("스마트워크"),
    HOME("재택근무");

    private String name;
}