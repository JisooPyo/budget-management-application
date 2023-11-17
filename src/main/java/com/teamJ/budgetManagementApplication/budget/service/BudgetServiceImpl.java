package com.teamJ.budgetManagementApplication.budget.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
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
        checkBudgetExists(year, month);

        int totalBudget = getTotalBudget(budgetSetRequestDto);
        Budget budget = Budget.builder().user(targetUser)
                .year(year).month(month).money(totalBudget).build();
        budgetRepository.save(budget);

        Budget targetBudget = findTargetBudget(year, month);
        budgetCategoryService.saveBudgetCategory(targetBudget, budgetSetRequestDto);
    }

    private int getTotalBudget(BudgetSetRequestDto budgetSetRequestDto) {
        return budgetSetRequestDto.getFood()
                + budgetSetRequestDto.getNecessaries()
                + budgetSetRequestDto.getEducation()
                + budgetSetRequestDto.getCulture()
                + budgetSetRequestDto.getEntertainment()
                + budgetSetRequestDto.getTransportation();
    }

    private void checkBudgetExists(int year, int month) {
        if (budgetRepository.findByYearAndMonth(year, month).isPresent()) {
            throw new CustomException(CustomErrorCode.BUDGET_ALREADY_EXISTS);
        }
    }

    private Budget findTargetBudget(int year, int month) {
        return budgetRepository.findByYearAndMonth(year, month).orElseThrow(
                () -> new CustomException(CustomErrorCode.BUDGET_NOT_FOUND)
        );
    }
}
