package com.teamJ.budgetManagementApplication.budgetCategory.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetUpdateRequestDto;
import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import com.teamJ.budgetManagementApplication.budgetCategory.repository.BudgetCategoryRepository;
import com.teamJ.budgetManagementApplication.category.service.CategoryServiceImpl;
import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
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
public class BudgetCategoryServiceImpl implements BudgetCategoryService {
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final CategoryServiceImpl categoryService;

    public void saveBudgetCategory(Budget targetBudget, BudgetSetRequestDto dto) {
        List<BudgetCategory> budgetCategories = new ArrayList<>();
        budgetCategories.add(createFoodBudget(targetBudget, dto.getFood()));
        budgetCategories.add(createNecessariesBudget(targetBudget, dto.getNecessaries()));
        budgetCategories.add(createEducationBudget(targetBudget, dto.getEducation()));
        budgetCategories.add(createCultureBudget(targetBudget, dto.getCulture()));
        budgetCategories.add(createEntertainmentBudget(targetBudget, dto.getEntertainment()));
        budgetCategories.add(createTransportationBudget(targetBudget, dto.getTransportation()));

        budgetCategoryRepository.saveAll(budgetCategories);
    }

    public void updateBudget(Budget targetBudget, BudgetUpdateRequestDto requestDto) {
        Map<BudgetCategory, Integer> budgetCategories = new HashMap<>();
        budgetCategories.put(findFoodBudget(targetBudget), requestDto.getFood());
        budgetCategories.put(findNecessariesBudget(targetBudget), requestDto.getNecessaries());
        budgetCategories.put(findEducationBudget(targetBudget), requestDto.getEducation());
        budgetCategories.put(findCultureBudget(targetBudget), requestDto.getCulture());
        budgetCategories.put(findEntertainmentBudget(targetBudget), requestDto.getEntertainment());
        budgetCategories.put(findTransportationBudget(targetBudget), requestDto.getTransportation());
        budgetCategories.forEach((BudgetCategory::update));
    }

    private BudgetCategory findFoodBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findFood())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory findNecessariesBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findNecessaries())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory findEducationBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findEducation())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory findCultureBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findCulture())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory findEntertainmentBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findEntertainment())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory findTransportationBudget(Budget targetBudget) {
        return budgetCategoryRepository
                .findByBudgetAndCategory(targetBudget, categoryService.findTransportation())
                .orElseThrow(() -> new CustomException(CustomErrorCode.BUDGET_CATEGORY_NOT_FOUND));
    }

    private BudgetCategory createFoodBudget(Budget targetBudget, int food) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findFood())
                .money(food).build();
    }

    private BudgetCategory createNecessariesBudget(Budget targetBudget, int necessaries) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findNecessaries())
                .money(necessaries).build();
    }

    private BudgetCategory createEducationBudget(Budget targetBudget, int education) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findEducation())
                .money(education).build();
    }

    private BudgetCategory createCultureBudget(Budget targetBudget, int culture) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findCulture())
                .money(culture).build();
    }

    private BudgetCategory createEntertainmentBudget(Budget targetBudget, int entertainment) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findEntertainment())
                .money(entertainment).build();
    }

    private BudgetCategory createTransportationBudget(Budget targetBudget, int transportation) {
        return BudgetCategory.builder().budget(targetBudget)
                .category(categoryService.findTransportation())
                .money(transportation).build();
    }
}
