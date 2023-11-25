package com.teamJ.budgetManagementApplication.expense.service;

import com.teamJ.budgetManagementApplication.category.entity.Category;
import com.teamJ.budgetManagementApplication.category.service.CategoryServiceImpl;
import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
import com.teamJ.budgetManagementApplication.expense.dto.CategorySumResponseDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseListResponseDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseResponseDto;
import com.teamJ.budgetManagementApplication.expense.entity.Expense;
import com.teamJ.budgetManagementApplication.expense.repository.ExpenseRepository;
import com.teamJ.budgetManagementApplication.user.entity.User;
import com.teamJ.budgetManagementApplication.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

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

    @Override
    public ExpenseListResponseDto getAllExpenses(
            String start, String end, Long categoryId, Integer min, Integer max, User user) {
        userService.findUser(user.getAccount());
        validateDate(start, end);
        validateMin(min);
        List<Expense> expenseList = getExpenseList(start, end, categoryId, min, max, user);
        List<ExpenseResponseDto> expenseDtoList =
                expenseList.stream().map(Expense::toExpenseResponseDto).toList();
        List<CategorySumResponseDto> categorySums = getCategorySum(categoryId, expenseList);
        long totalSum = getTotalSum(expenseList);

        return ExpenseListResponseDto.builder()
                .sum(totalSum)
                .categorySumList(categorySums)
                .expenseList(expenseDtoList).build();
    }

    private void validateMin(Integer min) {
        if (min < 0) {
            throw new CustomException(CustomErrorCode.MIN_MUST_BE_NOT_NEGATIVE);
        }
    }

    private long getTotalSum(List<Expense> expenseList) {
        long totalSum = 0L;
        for (Expense expense : expenseList) {
            if (expense.isExcludeFromSum()) {
                continue;
            }
            totalSum += expense.getMoney();
        }
        return totalSum;
    }

    private List<CategorySumResponseDto> getCategorySum(Long categoryId, List<Expense> expenseList) {
        if (categoryId != null) {
            return null;
        }
        // 카테고리, 합계
        Map<String, Long> categorySum = getCategorySumMap(expenseList);
        return getCategorySumDtoList(categorySum);
    }

    private List<CategorySumResponseDto> getCategorySumDtoList(Map<String, Long> categorySum) {
        List<CategorySumResponseDto> categorySumDtoList = new ArrayList<>();
        for (String category : categorySum.keySet()) {
            categorySumDtoList.add(
                    CategorySumResponseDto.builder()
                            .category(category)
                            .sum(categorySum.get(category))
                            .build()
            );
        }
        categorySumDtoList.sort(Comparator.comparing(CategorySumResponseDto::getCategory));
        return categorySumDtoList;
    }

    private Map<String, Long> getCategorySumMap(List<Expense> expenseList) {
        Map<String, Long> categorySum = new HashMap<>();
        for (Expense expense : expenseList) {
            if (expense.isExcludeFromSum()) {
                continue;
            }
            String categoryName = expense.getCategory().getName();
            categorySum.put(
                    categoryName,
                    categorySum.getOrDefault(categoryName, 0L) + expense.getMoney()
            );
        }
        return categorySum;
    }

    private List<Expense> getExpenseList(
            String start, String end, Long categoryId, Integer min, Integer max, User user) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
        return expenseRepository.getFilteredExpenses(startDate, endDate, categoryId, min, max, user);
    }

    private void validateDate(String start, String end) {
        if (start == null || end == null) {
            throw new CustomException(CustomErrorCode.REQUIRED_DATE_PARAMETER);
        }
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", start)
                || !Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", end)) {
            throw new CustomException(CustomErrorCode.INVALID_DATE_FORMAT);
        }
    }

    private void checkExpense(User targetUser) {
        if (expenseRepository.findByUser(targetUser).isPresent()) {
            throw new CustomException(CustomErrorCode.EXPENSE_ALREADY_EXISTS);
        }
    }
}
