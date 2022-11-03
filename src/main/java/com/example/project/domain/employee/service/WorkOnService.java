package com.example.project.domain.employee.service;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.employee.domain.repository.DailyRecordRepository;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkOnService {

    private final DailyRecordRepository dailyRecordRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        dailyRecordRepository.save(DailyRecord
                .builder()
                .user(user)
                .recordStart(LocalDateTime.now())
                .recordEnd(null)
                .build());
    }
}