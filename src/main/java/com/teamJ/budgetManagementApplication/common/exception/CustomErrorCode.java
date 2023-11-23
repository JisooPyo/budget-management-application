package com.teamJ.budgetManagementApplication.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 사용자입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "사용자가 존재하지 않습니다."),
    BUDGET_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 설정된 예산입니다."),
    BUDGET_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "예산이 존재하지 않습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "카테고리가 존재하지 않습니다."),
    ACCESS_DENIED(HttpStatus.BAD_REQUEST.value(), "잘못된 접근입니다."),
    BUDGET_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "예산-카테고리가 존재하지 않습니다.");

    private final int errorCode;
    private final String errorMessage;
}
