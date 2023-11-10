package com.teamJ.budgetManagementApplication.category.service;

import com.teamJ.budgetManagementApplication.category.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    /**
     * 모든 카테고리 목록을 반환합니다.
     *
     * @return 모든 카테고리 목록
     */
    List<CategoryResponseDto> getCategories();
}
