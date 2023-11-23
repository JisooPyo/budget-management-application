package com.teamJ.budgetManagementApplication.budget.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import static com.teamJ.budgetManagementApplication.budgetCategory.entity.QBudgetCategory.budgetCategory;

@Repository
@RequiredArgsConstructor
public class BudgetRepositoryImpl implements BudgetCustomRepository {
    private final JPAQueryFactory queryFactory;

    // 서비스단에서 데이터를 이용하기 편하게 Map으로 바꿔서 넘겨줍니다.
    @Override
    public Map<Long, Double> getCategoryAverage() {
        Map<Long, Double> categoryAverage = new HashMap<>();
        List<Long> categoryIds = LongStream.rangeClosed(1, 6).boxed().toList();
        List<Tuple> totalAverage = getTotalAverage(categoryIds);
        for (Tuple tuple : totalAverage) {
            Long categoryId = tuple.get(budgetCategory.category.id);
            Double average = tuple.get(budgetCategory.money.avg().as("money_avg"));
            categoryAverage.put(categoryId, average);
        }

        return categoryAverage;
    }

    // category별 평균 금액을 가져옵니다.
    private List<Tuple> getTotalAverage(List<Long> categoryIds) {
        return queryFactory
                .select(budgetCategory.category.id, budgetCategory.money.avg().as("money_avg"))
                .from(budgetCategory)
                .where(budgetCategory.category.id.in(categoryIds))
                .groupBy(budgetCategory.category.id)
                .fetch();
    }

}
