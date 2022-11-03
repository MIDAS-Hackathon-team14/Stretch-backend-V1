package com.example.project.domain.employee.service;

import com.example.project.domain.employee.domain.Plan;
import com.example.project.domain.employee.domain.repository.PlanRepository;
import com.example.project.domain.employee.exception.PlanAlreadyExistException;
import com.example.project.domain.employee.presentation.dto.request.DoPlanRequest;
import com.example.project.domain.user.domain.User;
import com.example.project.domain.user.facade.UserFacade;
import com.example.project.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DoPlanService {

    private final PlanRepository planRepository;
    private final UserFacade userFacade;

    public void execute(DoPlanRequest request) {

        User user = userFacade.getCurrentUser();

        if (request.getPlanStart().toLocalDate() != request.getPlanEnd().toLocalDate()) {
            throw BadRequestException.EXCEPTION;
        }

        LocalDate planDate = request.getPlanStart().toLocalDate();
        if (planRepository.existsByStartTimeBetween(planDate.atStartOfDay(), planDate.plusDays(1).atStartOfDay())) {
            throw PlanAlreadyExistException.EXCEPTION;
        }

        if(request.getIsOutOfOffice() && request.getOutOfOfficeType() == null
            || !request.getIsOutOfOffice() && request.getPlace() == null) {
            throw BadRequestException.EXCEPTION;
        }

        planRepository.save(Plan
                .builder()
                .isOutOfOffice(request.getIsOutOfOffice())
                .outOfOfficeType(request.getOutOfOfficeType())
                .place(request.getPlace())
                .startTime(request.getPlanStart())
                .endTime(request.getPlanEnd())
                .user(user)
                .build()
        );
    }
}