package com.example.project.domain.employee.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OutOfOfficeType {
    BUSINESS_TRIP("출장"),
    OUTSIDE_WORK("외근"),
    VACATION("휴가");

    private String name;
}