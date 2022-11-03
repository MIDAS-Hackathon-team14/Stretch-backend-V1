package com.example.project.domain.employee.exception;

import com.example.project.global.error.exception.BusinessException;
import com.example.project.global.error.exception.ErrorCode;

public class PlanAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new PlanAlreadyExistException();

    private PlanAlreadyExistException() {
        super(ErrorCode.PLAN_ALREADY_EXIST);
    }
}