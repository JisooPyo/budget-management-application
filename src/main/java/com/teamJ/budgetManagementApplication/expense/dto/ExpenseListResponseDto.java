package com.teamJ.budgetManagementApplication.expense.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExpenseListResponseDto {
    private long sum;
    private List<CategorySumResponseDto> categorySumList;
    private List<ExpenseResponseDto> expenseList;
}
