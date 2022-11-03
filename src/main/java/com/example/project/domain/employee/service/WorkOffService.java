package com.example.project.domain.employee.service;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.employee.domain.repository.DailyRecordRepository;
import com.example.project.domain.employee.domain.repository.PlanRepository;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class WorkOffService {

    private final DailyRecordRepository dailyRecordRepository;
    private final PlanRepository planRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        LocalDateTime today = LocalDateTime.now();
        DailyRecord dailyRecord = dailyRecordRepository.queryFirstByRecordStartBetweenAndUser(
                LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)),
                LocalDateTime.of(today.toLocalDate(), LocalTime.of(23, 59)), user
        );

        Plan plan = planRepository.queryFirstByStartTimeBetweenAndUser(
                LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)),
                LocalDateTime.of(today.toLocalDate(), LocalTime.of(23, 59)), user
        );

        dailyRecord.recordWorkOff(LocalDateTime.now(), plan);

        dailyRecordRepository.save(dailyRecord);
    }
}