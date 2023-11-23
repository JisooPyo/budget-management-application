package com.teamJ.budgetManagementApplication.budget.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignResponseDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetUpdateRequestDto;
import com.teamJ.budgetManagementApplication.user.entity.User;

import java.util.List;

public interface BudgetService {
    /**
     * 예산 설정
     *
     * @param budgetSetRequestDto 예산 설정을 위한 요청 정보
     * @param user                인증된 유저 정보
     */
    void createBudget(BudgetSetRequestDto budgetSetRequestDto, User user);

    /**
     * 예산 수정
     *
     * @param id         수정할 예산 식별자
     * @param requestDto 예산 수정을 위한 요청 정보
     * @param user       인증된 유저 정보
     */
    void updateBudget(Long id, BudgetUpdateRequestDto requestDto, User user);

    /**
     * 예산 설계
     *
     * @param requestDto 예산 설계에 필요한 총액
     * @param user       인증된 유저 정보
     * @return 설계된 예산의 결과 : 기존 유저들의 평균
     */
    List<BudgetDesignResponseDto> designBudget(BudgetDesignRequestDto requestDto, User user);
}
