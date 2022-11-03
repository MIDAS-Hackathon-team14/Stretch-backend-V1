package com.example.project.global.exception;

import com.example.project.global.error.exception.BusinessException;
import com.example.project.global.error.exception.ErrorCode;

public class ForbiddenException extends BusinessException {
    public static final BusinessException EXCEPTION = new ForbiddenException();
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }
}