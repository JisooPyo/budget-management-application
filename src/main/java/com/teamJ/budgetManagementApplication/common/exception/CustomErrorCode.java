package com.teamJ.budgetManagementApplication.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 사용자입니다.");

    private final int errorCode;
    private final String errorMessage;
}
