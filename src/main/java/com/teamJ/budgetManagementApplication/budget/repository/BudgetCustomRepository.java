package com.teamJ.budgetManagementApplication.budget.repository;

import java.util.Map;

public interface BudgetCustomRepository {

    Map<Long, Double> getCategoryAverage();
}
