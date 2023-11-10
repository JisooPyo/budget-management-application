package com.teamJ.budgetManagementApplication.category.entity;

import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<BudgetCategory> budgetCategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses = new ArrayList<>();
}
