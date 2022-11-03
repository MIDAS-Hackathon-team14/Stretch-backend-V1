package com.example.project.domain.employee.domain.repository;

import com.example.project.domain.employee.domain.Plan;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    boolean existsByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Plan> queryByStartTimeBetween(LocalDateTime startTime, LocalDateTime end);
}