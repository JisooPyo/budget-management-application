package com.teamJ.budgetManagementApplication.budgetCategory.service;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.budget.entity.Budget;
import com.teamJ.budgetManagementApplication.budgetCategory.entity.BudgetCategory;
import com.teamJ.budgetManagementApplication.budgetCategory.repository.BudgetCategoryRepository;
import com.teamJ.budgetManagementApplication.category.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
