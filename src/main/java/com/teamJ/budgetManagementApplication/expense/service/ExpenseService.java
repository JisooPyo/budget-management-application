package com.teamJ.budgetManagementApplication.expense.service;

import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseListResponseDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseResponseDto;
import com.teamJ.budgetManagementApplication.user.entity.User;

public interface ExpenseService {
    /**
     * 지출 생성
     *
     * @param requestDto 지출 기록에 필요한 요청 정보
     * @param user       인증된 유저 정보
     */
    void createExpense(ExpenseCreateRequestDto requestDto, User user);

    /**
     * 지출 목록 읽기
     *
     * @param start      지출을 조회하는 시작날짜
     * @param end        지출을 조회하는 끝 날짜
     * @param categoryId 카테고리별 지출을 조회할 때 쓰이는 카테고리 식별자
     * @param min        지출의 최솟값
     * @param max        지출의 최댓값
     * @param user       인증된 유저 정보
     * @return 지출 목록(전체 지출 합, 카테고리별 지출 합, 지출 목록 조회)
     */
    ExpenseListResponseDto getAllExpenses(String start, String end, Long categoryId, Integer min, Integer max, User user);

    /**
     * 지출 상세 조회
     *
     * @param id   지출 식별자
     * @param user 인증된 유저 정보
     * @return 조회된 지출의 정보
     */
    ExpenseResponseDto getExpense(Long id, User user);
}
