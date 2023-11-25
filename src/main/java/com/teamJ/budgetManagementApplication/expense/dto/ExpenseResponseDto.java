package com.teamJ.budgetManagementApplication.expense.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ExpenseResponseDto {
    private Long id;
    private String category;
    private LocalDate localDate;
    private Integer money;
    private String memo;
}
