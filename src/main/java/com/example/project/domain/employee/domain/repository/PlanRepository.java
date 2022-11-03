package com.example.project.domain.employee.domain.repository;

import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long> {
    boolean existsByStartTimeBetweenAndUser(LocalDateTime start, LocalDateTime end, User user);
    Plan queryFirstByStartTimeBetweenAndUser(LocalDateTime startTime, LocalDateTime end, User user);
    List<Plan> queryByStartTimeBetweenAndUser(LocalDateTime startTime, LocalDateTime end, User user);
}