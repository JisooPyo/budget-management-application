package com.teamJ.budgetManagementApplication.category.dto;

import com.teamJ.budgetManagementApplication.category.entity.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {
    private Long id;
    private String name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
