package com.teamJ.budgetManagementApplication.expense.service;

import com.teamJ.budgetManagementApplication.category.entity.Category;
import com.teamJ.budgetManagementApplication.category.service.CategoryServiceImpl;
import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import com.teamJ.budgetManagementApplication.expense.repository.ExpenseRepository;
import com.teamJ.budgetManagementApplication.user.entity.User;
import com.teamJ.budgetManagementApplication.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;

    @Override
    public void createExpense(ExpenseCreateRequestDto requestDto, User user) {
        User targetUser = userService.findUser(user.getAccount());
        checkExpense(targetUser);
        Category category = categoryService.findCategory(requestDto.getCategoryId());
        Expense expense = Expense.builder().user(targetUser).category(category)
                .date(requestDto.getDate())
                .money(requestDto.getMoney())
                .memo(requestDto.getMemo())
                .excludeFromSum(requestDto.isExcludeFromSum())
                .build();
        expenseRepository.save(expense);
    }

    private void checkExpense(User targetUser) {
        if (expenseRepository.findByUser(targetUser).isPresent()) {
            throw new CustomException(CustomErrorCode.EXPENSE_ALREADY_EXISTS);
        }
    }
}
