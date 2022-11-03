package com.example.project.domain.employee.domain.repository;

import com.example.project.domain.employee.domain.DailyRecord;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DailyRecordRepository extends CrudRepository<DailyRecord, Long> {
    List<DailyRecord> queryByRecordStartBetween(LocalDateTime planStart, LocalDateTime planStart2);
    DailyRecord queryFirstByRecordStartBetween(LocalDateTime planStart, LocalDateTime planStart2);
}