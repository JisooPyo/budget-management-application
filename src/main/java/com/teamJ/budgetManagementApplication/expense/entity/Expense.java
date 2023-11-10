package com.teamJ.budgetManagementApplication.expense.entity;

import com.teamJ.budgetManagementApplication.category.entity.Category;
import com.teamJ.budgetManagementApplication.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer money;

    @Column(nullable = false)
    private String memo;

    @Column(nullable = false)
    private boolean excludeFromSum = false;
}