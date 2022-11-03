package com.example.project.global.exception;

import com.example.project.global.error.exception.BusinessException;
import com.example.project.global.error.exception.ErrorCode;

public class BadRequestException extends BusinessException {
    public static final BusinessException EXCEPTION = new BadRequestException();

    private BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}