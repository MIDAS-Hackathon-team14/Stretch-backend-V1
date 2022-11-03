package com.example.project.domain.company.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FlexTime {
    TIME_DIFFERENCE("시차 출퇴근"),
    SELECT("근무시간 선택"),
    INTENSIVE("집약 근무");

    private String name;
}