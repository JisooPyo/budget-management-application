package com.teamJ.budgetManagementApplication.budget.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BudgetUpdateRequestDto {
    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer food;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer necessaries;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer education;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer culture;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer entertainment;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private Integer transportation;
}
