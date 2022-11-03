package com.example.project.global.exception;

import com.example.project.global.error.exception.BusinessException;
import com.example.project.global.error.exception.ErrorCode;

public class InvalidTokenException extends BusinessException {
    public static final BusinessException EXCEPTION = new InvalidTokenException();
    private InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN);
    }
}