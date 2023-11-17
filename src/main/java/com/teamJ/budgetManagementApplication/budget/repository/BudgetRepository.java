package com.teamJ.budgetManagementApplication.budget.repository;

import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByYearAndMonth(Integer year, Integer month);
}
