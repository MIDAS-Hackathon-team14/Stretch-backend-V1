package com.example.project.domain.company.exception;

import com.example.project.global.error.exception.BusinessException;
import com.example.project.global.error.exception.ErrorCode;

public class CompanyNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new CompanyNotFoundException();
    private CompanyNotFoundException(){
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}