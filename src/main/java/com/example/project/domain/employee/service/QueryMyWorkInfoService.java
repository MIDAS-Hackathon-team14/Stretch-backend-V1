package com.example.project.domain.employee.service;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.employee.domain.repository.DailyRecordRepository;
import com.example.project.domain.employee.domain.repository.PlanRepository;
import com.example.project.domain.employee.presentation.dto.response.WorkPlanInfoResponse;
import com.example.project.domain.employee.presentation.dto.response.WorkPlanInfoResponse.WorkPlanResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryMyWorkInfoService {

    private final DailyRecordRepository dailyRecordRepository;
    private final PlanRepository planRepository;
    private final UserFacade userFacade;

    public WorkPlanInfoResponse execute() {

        User user = userFacade.getCurrentUser();

        LocalDateTime today = LocalDateTime.now();
        Integer dayOfWeek = today.toLocalDate().getDayOfWeek().getValue();

        LocalDateTime startWeek = LocalDateTime.of(today.toLocalDate().minusDays(dayOfWeek - 1), LocalTime.of(0, 0));
        LocalDateTime endWeek = LocalDateTime.of(today.toLocalDate().plusDays(7 - dayOfWeek), LocalTime.of(23, 59));


        List<Plan> plans = planRepository.queryByStartTimeBetweenAndUser(startWeek, endWeek, user);
        List<DailyRecord> dailyRecords = dailyRecordRepository.queryByRecordStartBetweenAndUser(startWeek, endWeek, user);
        List<WorkPlanResponse> planResponseList = new ArrayList<>();

        int recordSum = 0;
        int plansIdx = 0;
        int dailyRecordsIdx = 0;
        for (LocalDateTime i = startWeek; !i.isAfter(endWeek); i = i.plusDays(1)) {
            if (!i.isAfter(LocalDateTime.now())) { //DailyRecord가 생성된 상황 (출근한 날)
                DailyRecord dailyRecord = dailyRecords.get(dailyRecordsIdx++);
                Plan plan = plans.get(plansIdx++);
                planResponseList.add(WorkPlanResponse
                        .builder()
                        .isPlaned(true)
                        .date(i.toLocalDate())
                        .isOutOfOffice(plan.getIsOutOfOffice())
                        .outOfOfficeType(plan.getOutOfOfficeType().getName())
                        .planStart(plan.getStartTime())
                        .planEnd(plan.getEndTime())
                        .recordStart(dailyRecord.getRecordStart())
                        .recordEnd(dailyRecord.getRecordEnd())
                        .recordSum((int) ChronoUnit.MINUTES.between(
                                dailyRecord.getRecordStart(),
                                dailyRecord.getRecordEnd() == null ?
                                        LocalDateTime.now() :
                                        dailyRecord.getRecordEnd()))
                        .build()
                );
                recordSum += dailyRecord.getRecordEnd().compareTo(dailyRecord.getRecordStart());
            } else if (plans.get(plansIdx).getStartTime().toLocalDate() == i.toLocalDate()) { //Plan이 생성된 날
                Plan plan = plans.get(plansIdx++);
                planResponseList.add(WorkPlanResponse.builder()
                        .isPlaned(true)
                        .date(i.toLocalDate())
                        .isOutOfOffice(plan.getIsOutOfOffice())
                        .outOfOfficeType(plan.getOutOfOfficeType().getName())
                        .planStart(plan.getStartTime())
                        .planEnd(plan.getEndTime())
                        .recordStart(LocalDateTime.of(i.toLocalDate(), LocalTime.of(0,0)))
                        .recordEnd(LocalDateTime.of(i.toLocalDate(), LocalTime.of(0,0)))
                        .build()
                );
            } else { //그 외
                planResponseList.add(WorkPlanResponse.builder()
                        .isPlaned(false)
                        .date(i.toLocalDate())
                        .build()
                );
            }
        }

        return WorkPlanInfoResponse
                .builder()
                .recordSum(recordSum)
                .weekPlan(planResponseList)
                .build();
    }
}