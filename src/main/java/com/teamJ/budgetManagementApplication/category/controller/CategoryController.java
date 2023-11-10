package com.teamJ.budgetManagementApplication.category.controller;

import com.teamJ.budgetManagementApplication.category.dto.CategoryResponseDto;
import com.teamJ.budgetManagementApplication.category.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Category API", description = "Category에 대한 API 정보를 담고 있습니다.")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "카테고리 목록 가져오기",
            description = "모든 카테고리 목록을 반환합니다.")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> results = categoryService.getCategories();
        return ResponseEntity.ok().body(results);
    }

}
