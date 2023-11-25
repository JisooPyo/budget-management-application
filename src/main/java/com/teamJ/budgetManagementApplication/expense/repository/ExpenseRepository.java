package com.teamJ.budgetManagementApplication.expense.repository;

import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import com.teamJ.budgetManagementApplication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>, ExpenseCustomRepository {
    Optional<Expense> findByUser(User user);
}
