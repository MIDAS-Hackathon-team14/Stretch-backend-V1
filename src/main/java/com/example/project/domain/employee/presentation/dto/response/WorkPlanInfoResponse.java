package com.example.project.domain.employee.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class WorkPlanInfoResponse {

    private Integer recordSum;
    private List<WorkPlanResponse> weekPlan;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WorkPlanResponse {
        private LocalDate date;
        private Boolean isPlaned;

        private Boolean isOutOfOffice;
        private String outOfOfficeType;

        private LocalDateTime planStart;
        private LocalDateTime planEnd;

        private LocalDateTime recordStart;
        private LocalDateTime recordEnd;
        private Integer recordSum;
    }
}