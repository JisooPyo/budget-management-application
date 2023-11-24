package com.teamJ.budgetManagementApplication.expense.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategorySumResponseDto {
    private String category;
    private long sum;
}
