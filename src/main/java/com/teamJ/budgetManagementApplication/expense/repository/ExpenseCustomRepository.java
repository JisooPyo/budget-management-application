package com.teamJ.budgetManagementApplication.expense.repository;

import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import com.teamJ.budgetManagementApplication.user.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseCustomRepository {
    List<Expense> getFilteredExpenses(
            LocalDate startDate, LocalDate endDate, Long categoryId, Integer min, Integer max, User user);
}
