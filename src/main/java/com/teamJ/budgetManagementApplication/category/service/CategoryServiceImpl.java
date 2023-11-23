package com.teamJ.budgetManagementApplication.category.service;

import com.teamJ.budgetManagementApplication.category.dto.CategoryResponseDto;
import com.teamJ.budgetManagementApplication.category.entity.Category;
import com.teamJ.budgetManagementApplication.category.reposiroty.CategoryRepository;
import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
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

    public Category findFood() {
        return categoryRepository.findByName("식비").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findNecessaries() {
        return categoryRepository.findByName("생활용품").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findEducation() {
        return categoryRepository.findByName("교육").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findCulture() {
        return categoryRepository.findByName("문화").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findEntertainment() {
        return categoryRepository.findByName("오락").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findTransportation() {
        return categoryRepository.findByName("교통").orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.CATEGORY_NOT_FOUND)
        );
    }
}
