package com.teamJ.budgetManagementApplication.expense.dto;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ExpenseUpdateRequestDto {
    @Min(value = 1, message = "카테고리 ID는 1 이상이어야 합니다.")
    private Long categoryId;
    private LocalDate date;
    @Min(value = 1, message = "기록하는 지출금액은 0보다 커야 합니다.")
    private Integer money;
    private String memo;
    private Boolean excludeFromSum;
}
