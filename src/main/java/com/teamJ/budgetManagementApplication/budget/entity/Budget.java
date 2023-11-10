package com.teamJ.budgetManagementApplication.budget.entity;

import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import com.teamJ.budgetManagementApplication.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(uniqueConstraints =
        {@UniqueConstraint(columnNames = {"year", "month"})})
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer money;

    @OneToMany(mappedBy = "budget")
    private List<BudgetCategory> budgetCategories = new ArrayList<>();
}
