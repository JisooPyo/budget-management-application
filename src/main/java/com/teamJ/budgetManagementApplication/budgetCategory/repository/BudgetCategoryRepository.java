package com.teamJ.budgetManagementApplication.budgetCategory.repository;

import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
}
