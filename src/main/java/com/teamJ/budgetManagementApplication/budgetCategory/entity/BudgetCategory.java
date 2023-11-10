package com.teamJ.budgetManagementApplication.budgetCategory.entity;

import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import com.teamJ.budgetManagementApplication.category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private Integer money;
}
