package com.example.project.global.error.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    EXPIRED_TOKEN(401,"AUTH-401-1", "Expired Token"),
    INVALID_TOKEN(401,"AUTH-401-2","Invalid Token"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found" ),
    USER_ALREADY_EXIST(409, "USER-409-1", "User Already Exist"),

    COMPANY_NOT_FOUND(404, "COMPANY-404-1", "Company Not Found"),

    PLAN_ALREADY_EXIST(409, "PLAN-409-1", "Plan Already Exist"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),
    BAD_REQUEST(400, "COMMON-400-1", "Bad Request"),
    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final Integer status;
    private final String code;
    private final String message;
}