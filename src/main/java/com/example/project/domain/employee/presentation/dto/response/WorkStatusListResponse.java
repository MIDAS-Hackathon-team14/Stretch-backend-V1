package com.example.project.domain.employee.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WorkStatusListResponse {

    private List<WorkStatusResponse> weekPlan;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WorkStatusResponse {
        private Long userId;
        private String userName;
        private Boolean isWorking;

        private Boolean isOutOfOffice;
        private String outOfOfficeType;
        private String place;

        private LocalDateTime planStart;
        private LocalDateTime planEnd;

        private LocalDateTime recordStart;
        private LocalDateTime recordEnd;
        private Integer recordSum;
    }
}