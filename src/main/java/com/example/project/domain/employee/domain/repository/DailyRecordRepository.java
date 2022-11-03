package com.example.project.domain.employee.domain.repository;

import com.example.project.domain.employee.domain.DailyRecord;
import com.example.project.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyRecordRepository extends CrudRepository<DailyRecord, Long> {
    List<DailyRecord> queryByRecordStartBetween(LocalDateTime start, LocalDateTime end);
    List<DailyRecord> queryByRecordStartBetweenAndUser(LocalDateTime start, LocalDateTime end, User user);
    DailyRecord queryFirstByRecordStartBetweenAndUser(LocalDateTime start, LocalDateTime end, User user);

}