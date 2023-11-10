package com.teamJ.budgetManagementApplication.category.service;

import com.teamJ.budgetManagementApplication.category.dto.CategoryResponseDto;
import com.teamJ.budgetManagementApplication.category.entity.Category;
import com.teamJ.budgetManagementApplication.category.reposiroty.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByIdAsc();

        return categories.stream().map(CategoryResponseDto::new).toList();
    }
}
