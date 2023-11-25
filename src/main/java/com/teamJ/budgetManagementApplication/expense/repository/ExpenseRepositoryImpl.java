package com.teamJ.budgetManagementApplication.expense.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import com.teamJ.budgetManagementApplication.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.teamJ.budgetManagementApplication.expense.entity.QExpense.expense;

@Repository
@RequiredArgsConstructor
public class ExpenseRepositoryImpl implements ExpenseCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Expense> getFilteredExpenses(
            LocalDate startDate, LocalDate endDate, Long categoryId, Integer min, Integer max, User user) {

        BooleanBuilder whereBuilder = new BooleanBuilder();
        whereBuilder.and(expense.user.id.eq(user.getId()));
        whereBuilder.and(expense.date.between(startDate, endDate));

        if (categoryId != null) {
            whereBuilder.and(expense.category.id.eq(categoryId));
        }
        if (min != null) {
            whereBuilder.and(expense.money.goe(min));
        }
        if (max != null) {
            whereBuilder.and(expense.money.loe(max));
        }

        return queryFactory.selectFrom(expense)
                .where(whereBuilder)
                .orderBy(expense.date.desc())
                .fetch();
    }
}
