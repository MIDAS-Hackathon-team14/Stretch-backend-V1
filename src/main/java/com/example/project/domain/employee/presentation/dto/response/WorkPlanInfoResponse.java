package com.example.project.domain.employee.presentation.dto.response;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.employee.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

        public void setPlan(Plan plan) {
            this.isPlaned = true;
            this.isOutOfOffice = plan.getIsOutOfOffice();
            this.outOfOfficeType = plan.getOutOfOfficeType().getName();
            this.planStart = plan.getStartTime();
            this.planEnd = plan.getEndTime();
            this.recordStart = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(0, 0));
            this.recordEnd = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(0, 0));
            this.recordSum = 0;
        }

        public void setDailyRecord(DailyRecord dailyRecord) {

            this.recordStart = dailyRecord.getRecordStart();
            this.recordEnd = dailyRecord.getRecordEnd();
            this.recordSum = (int) ChronoUnit.MINUTES.between(
                    dailyRecord.getRecordStart(),
                    dailyRecord.getRecordEnd() == null ?
                            LocalDateTime.now() :
                            dailyRecord.getRecordEnd());
        }
    }
}