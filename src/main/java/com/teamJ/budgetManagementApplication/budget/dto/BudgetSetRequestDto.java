package com.teamJ.budgetManagementApplication.budget.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BudgetSetRequestDto {
    @Min(value = 2022, message = "2022년도부터 가능합니다.")
    private int year;

    @Min(value = 1, message = "잘못된 입력입니다.")
    @Max(value = 12, message = "잘못된 입력입니다.")
    private int month;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int food;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int necessaries;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int education;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int culture;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int entertainment;

    @Min(value = 0, message = "예산에 음수는 허락되지 않습니다.")
    private int transportation;
}
