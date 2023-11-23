package com.teamJ.budgetManagementApplication.budget.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BudgetDesignResponseDto {
    private String category;
    private int amount;
}
