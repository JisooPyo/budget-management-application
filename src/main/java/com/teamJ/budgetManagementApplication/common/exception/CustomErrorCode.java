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
    BUDGET_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "예산-카테고리가 존재하지 않습니다."),
    EXPENSE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 지출입니다."),
    REQUIRED_DATE_PARAMETER(HttpStatus.BAD_REQUEST.value(), "날짜는 조건에 꼭 들어가야 합니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST.value(), "알맞은 날짜 형식이 아닙니다."),
    MIN_MUST_BE_NOT_NEGATIVE(HttpStatus.BAD_REQUEST.value(), "최솟값은 0이상의 양수여야 합니다.");

    private final int errorCode;
    private final String errorMessage;
}
