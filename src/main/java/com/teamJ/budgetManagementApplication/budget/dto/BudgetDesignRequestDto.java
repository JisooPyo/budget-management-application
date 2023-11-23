package com.teamJ.budgetManagementApplication.budget.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDesignRequestDto {
    @NotBlank
    private int total;
}
