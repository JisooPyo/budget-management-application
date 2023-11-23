package com.teamJ.budgetManagementApplication.expense.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ExpenseCreateRequestDto {
    @Min(value = 1, message = "카테고리 ID는 1 이상이어야 합니다.")
    private long categoryId;
    @NotNull
    private LocalDate date;
    @Min(value = 1, message = "기록하는 지출금액은 0보다 커야 합니다.")
    private int money;
    @NotNull
    private String memo;
    private boolean excludeFromSum;
}
