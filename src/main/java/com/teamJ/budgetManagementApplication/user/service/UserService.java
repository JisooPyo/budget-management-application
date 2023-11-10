package com.teamJ.budgetManagementApplication.user.service;

import com.teamJ.budgetManagementApplication.user.dto.SignupRequestDto;

public interface UserService {
    /**
     * 회원가입
     *
     * @param requestDto 사용자 회원 가입을 위한 요청 정보
     */
    void signup(SignupRequestDto requestDto);
}
