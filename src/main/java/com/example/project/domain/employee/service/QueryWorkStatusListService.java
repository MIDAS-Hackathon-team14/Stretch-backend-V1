package com.example.project.domain.employee.service;

import com.example.project.domain.company.domain.enums.FlexPlace;
import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.employee.domain.repository.DailyRecordRepository;
import com.example.project.domain.employee.presentation.dto.response.WorkStatusListResponse;
import com.example.project.domain.employee.presentation.dto.response.WorkStatusListResponse.WorkStatusResponse;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QueryWorkStatusListService {

    private final DailyRecordRepository dailyRecordRepository;
    private final UserRepository userRepository;

    public WorkStatusListResponse execute() {

        LocalDateTime today = LocalDateTime.now();

        List<User> users = userRepository.findBy();

        List<WorkStatusResponse> dailyRecords = dailyRecordRepository
                .queryByRecordStartBetween(
                        LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)),
                        LocalDateTime.of(today.toLocalDate(), LocalTime.of(23, 59))
                ).stream()
                .map(o -> {
                    Plan plan = o.getPlan();
                    System.out.println(users.size());
                    users.remove(o.getUser());
                    System.out.println(users.size());
                    return WorkStatusResponse
                            .builder()
                            .userId(o.getUser().getId())
                            .userName(o.getUser().getName())
                            .isWorking(o.getRecordEnd() == null)
                            .isOutOfOffice(plan.getIsOutOfOffice())
                            .outOfOfficeType(plan.getOutOfOfficeType().getName())
                            .place(plan.getPlace().getName())
                            .planStart(plan.getStartTime())
                            .planEnd(plan.getEndTime())
                            .recordStart(o.getRecordStart())
                            .recordEnd(o.getRecordEnd())
                            .recordSum(o.getRecordSum())
                            .build();
                })
                .collect(Collectors.toList());

        for (User user : users) {
            dailyRecords.add(
                    WorkStatusResponse
                            .builder()
                            .userId(user.getId())
                            .userName(user.getName())
                            .isWorking(false)
                            .isOutOfOffice(false)
                            .place(FlexPlace.COMPANY.getName())
                            .planStart(LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)))
                            .planEnd(LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)))
                            .recordStart(LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)))
                            .recordEnd(LocalDateTime.of(today.toLocalDate(), LocalTime.of(0, 0)))
                            .recordSum(0)
                            .build()
            );
        }

        return new WorkStatusListResponse(dailyRecords);
    }
}