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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

        LocalDate startWeek = today.toLocalDate().minusDays(dayOfWeek - 1);
        LocalDate endWeek = today.toLocalDate().plusDays(7 - dayOfWeek);

        List<Plan> plans = planRepository.queryByStartTimeBetweenAndUser(LocalDateTime.of(startWeek,LocalTime.of(0,0)), LocalDateTime.of(endWeek,LocalTime.of(23,59)), user);
        List<DailyRecord> dailyRecords = dailyRecordRepository.queryByRecordStartBetweenAndUser(LocalDateTime.of(startWeek,LocalTime.of(0,0)), LocalDateTime.of(endWeek,LocalTime.of(23,59)), user);
        List<WorkPlanInfoResponse.WorkPlanResponse> planResponseList = new ArrayList<>();

        int recordSum = 0;
        int plansIdx = 0;
        int dailyRecordsIdx = 0;
        for (LocalDate i = startWeek; !i.isAfter(endWeek); i = i.plusDays(1)) {
            if (!i.isAfter(LocalDateTime.now().toLocalDate())) { //DailyRecord가 생성된 상황 (출근한 날)
                DailyRecord dailyRecord = dailyRecords.get(dailyRecordsIdx++);
                Plan plan = plans.get(plansIdx++);
                planResponseList.add(WorkPlanInfoResponse.WorkPlanResponse
                        .builder()
                        .isPlaned(true)
                        .date(i)
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
            } else if (plans.get(plansIdx).getStartTime().toLocalDate().equals(i)) { //Plan이 생성된 날
                Plan plan = plans.get(plansIdx++);
                planResponseList.add(WorkPlanInfoResponse.WorkPlanResponse.builder()
                        .isPlaned(true)
                        .date(i)
                        .isOutOfOffice(plan.getIsOutOfOffice())
                        .outOfOfficeType(plan.getOutOfOfficeType().getName())
                        .planStart(plan.getStartTime())
                        .planEnd(plan.getEndTime())
                        .recordStart(LocalDateTime.of(i, LocalTime.of(0,0)))
                        .recordEnd(LocalDateTime.of(i, LocalTime.of(0,0)))
                        .build()
                );
            } else { //그 외
                planResponseList.add(WorkPlanInfoResponse.WorkPlanResponse.builder()
                        .isPlaned(false)
                        .date(i)
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