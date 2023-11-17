package com.teamJ.budgetManagementApplication.budget.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.user.entity.User;

public interface BudgetService {
    /**
     * 예산 설정
     *
     * @param budgetSetRequestDto 예산 설정을 위한 요청 정보
     * @param user                인증된 유저 정보
     */
    void createBudget(BudgetSetRequestDto budgetSetRequestDto, User user);
}
