package com.teamJ.budgetManagementApplication.budget.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignResponseDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetUpdateRequestDto;
import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import com.teamJ.budgetManagementApplication.budget.repository.BudgetRepository;
import com.teamJ.budgetManagementApplication.budgetCategory.service.BudgetCategoryServiceImpl;
import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
import com.teamJ.budgetManagementApplication.user.entity.User;
import com.teamJ.budgetManagementApplication.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final BudgetCategoryServiceImpl budgetCategoryService;
    private final UserServiceImpl userService;

    @Override
    public void createBudget(BudgetSetRequestDto budgetSetRequestDto, User user) {
        User targetUser = userService.findUser(user.getAccount());

        int year = budgetSetRequestDto.getYear();
        int month = budgetSetRequestDto.getMonth();
        checkBudgetExists(year, month, targetUser);

        int totalBudget = getTotalBudget(budgetSetRequestDto);
        Budget budget = Budget.builder().user(targetUser)
                .year(year).month(month).money(totalBudget).build();
        budgetRepository.save(budget);

        Budget targetBudget = findTargetBudget(year, month, targetUser);
        budgetCategoryService.saveBudgetCategory(targetBudget, budgetSetRequestDto);
    }

    @Override
    public void updateBudget(Long id, BudgetUpdateRequestDto requestDto, User user) {
        User targetUser = userService.findUser(user.getAccount());
        Budget targetBudget = findBudget(id);
        checkAuthorizedUser(targetBudget, targetUser);

        budgetCategoryService.updateBudget(targetBudget, requestDto);
    }

    @Override
    public List<BudgetDesignResponseDto> designBudget(BudgetDesignRequestDto requestDto, User user) {
        User targetUser = userService.findUser(user.getAccount());

        // 카테고리별 평균 금액(카테고리 ID, 카테고리별 평균금액)
        Map<Long, Double> totalAverage = budgetRepository.getCategoryAverage();
        double sum = totalAverage.values().stream().mapToDouble(Double::doubleValue).sum();

        // 카테고리별 비율 구하기
        Map<String, Double> totalRatio = getTotalRatio(totalAverage, sum);
        return getDesignBudget(totalRatio, requestDto.getTotal());
    }

    private List<BudgetDesignResponseDto> getDesignBudget(Map<String, Double> totalRatio, int total) {
        List<BudgetDesignResponseDto> results = new ArrayList<>();
        double etcRatio = 0;
        for (String category : totalRatio.keySet()) {
            if (totalRatio.get(category) < 10) {
                etcRatio += totalRatio.get(category);
                continue;
            }
            results.add(
                    BudgetDesignResponseDto.builder()
                            .category(category)
                            .amount((int) (Math.round(totalRatio.get(category) * total / 100 / 100) * 100))
                            .build()
            );
        }
        results.add(
                BudgetDesignResponseDto.builder().category("기타")
                        .amount((int) Math.round(etcRatio * total / 100 / 100) * 100).build()
        );
        return results;
    }

    private Map<String, Double> getTotalRatio(Map<Long, Double> totalAverage, double sum) {
        Map<Long, String> category = Map.of(
                1L, "식비", 2L, "생활용품", 3L, "교육",
                4L, "문화", 5L, "오락", 6L, "교통"
        );
        Map<String, Double> totalRatio = new HashMap<>();

        for (Long categoryId : totalAverage.keySet()) {
            String categoryName = category.get(categoryId);
            Double average = totalAverage.get(categoryId);
            totalRatio.put(categoryName, average / sum * 100);
        }
        return totalRatio;
    }

    private int getTotalBudget(BudgetSetRequestDto budgetSetRequestDto) {
        return budgetSetRequestDto.getFood()
                + budgetSetRequestDto.getNecessaries()
                + budgetSetRequestDto.getEducation()
                + budgetSetRequestDto.getCulture()
                + budgetSetRequestDto.getEntertainment()
                + budgetSetRequestDto.getTransportation();
    }

    private void checkBudgetExists(int year, int month, User targetUser) {
        if (budgetRepository.findByYearAndMonthAndUser(year, month, targetUser).isPresent()) {
            throw new CustomException(CustomErrorCode.BUDGET_ALREADY_EXISTS);
        }
    }

    private Budget findTargetBudget(int year, int month, User targetUser) {
        return budgetRepository.findByYearAndMonthAndUser(year, month, targetUser).orElseThrow(
                () -> new CustomException(CustomErrorCode.BUDGET_NOT_FOUND)
        );
    }

    private Budget findBudget(Long id) {
        return budgetRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.BUDGET_NOT_FOUND)
        );
    }

    private void checkAuthorizedUser(Budget budget, User user) {
        if (!budget.getUser().getAccount().equals(user.getAccount())) {
            throw new CustomException(CustomErrorCode.ACCESS_DENIED);
        }
    }
}
