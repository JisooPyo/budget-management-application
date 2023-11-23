package com.teamJ.budgetManagementApplication.budgetCategory.repository;

import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import com.teamJ.budgetManagementApplication.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    Optional<BudgetCategory> findByBudgetAndCategory(Budget budget, Category category);
}
