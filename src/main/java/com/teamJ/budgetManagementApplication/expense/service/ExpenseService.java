package com.teamJ.budgetManagementApplication.expense.service;

import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.user.entity.User;

public interface ExpenseService {
    /**
     * 지출 생성
     *
     * @param requestDto 지출 기록에 필요한 요청 정보
     * @param user       인증된 유저 정보
     */
    void createExpense(ExpenseCreateRequestDto requestDto, User user);
}
