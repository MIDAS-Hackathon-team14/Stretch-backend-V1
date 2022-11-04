package com.example.project.domain.employee.service;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.employee.domain.repository.DailyRecordRepository;
import com.example.project.domain.employee.domain.repository.PlanRepository;
import com.example.project.domain.employee.presentation.dto.response.WorkPlanInfoResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QueryUserWorkInfoService {

    private final DailyRecordRepository dailyRecordRepository;
    private final PlanRepository planRepository;
    private final UserFacade userFacade;

    public WorkPlanInfoResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);

        LocalDateTime today = LocalDateTime.now();
        Integer dayOfWeek = today.toLocalDate().getDayOfWeek().getValue();

        LocalDateTime startWeek = LocalDateTime.of(today.toLocalDate().minusDays(dayOfWeek - 1), LocalTime.of(0, 0));
        LocalDateTime endWeek = LocalDateTime.of(today.toLocalDate().plusDays(7 - dayOfWeek), LocalTime.of(23, 59));

        List<Plan> plans = planRepository.queryByStartTimeBetweenAndUserOrderByStartTime(startWeek, endWeek, user);
        List<DailyRecord> dailyRecords = dailyRecordRepository.queryByRecordStartBetweenAndUser(startWeek, endWeek, user);
        List<WorkPlanInfoResponse.WorkPlanResponse> planResponseList = new ArrayList<>();

        int recordSum = 0;
        int plansIdx = 0;
        int dailyRecordsIdx = 0;
        for (LocalDateTime i = startWeek; !i.isAfter(endWeek); i = i.plusDays(1)) {

            WorkPlanInfoResponse.WorkPlanResponse workPlanResponse = WorkPlanInfoResponse.WorkPlanResponse
                    .builder()
                    .isPlaned(false)
                    .date(i.toLocalDate()).build();

            if (plans.size() > plansIdx && plans.get(plansIdx).getStartTime().toLocalDate().equals(i.toLocalDate())) {
                workPlanResponse.setPlan(plans.get(plansIdx++));
            }

            if(dailyRecords.size() > dailyRecordsIdx && dailyRecords.get(dailyRecordsIdx).getRecordStart().toLocalDate().equals(i.toLocalDate())) {
                workPlanResponse.setDailyRecord(dailyRecords.get(dailyRecordsIdx));
            }

            planResponseList.add(workPlanResponse);
        }

        return WorkPlanInfoResponse
                .builder()
                .recordSum(recordSum)
                .weekPlan(planResponseList)
                .build();
    }
}