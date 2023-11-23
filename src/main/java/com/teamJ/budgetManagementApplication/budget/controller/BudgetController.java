package com.teamJ.budgetManagementApplication.budget.controller;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetDesignResponseDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
import com.teamJ.budgetManagementApplication.budget.dto.BudgetUpdateRequestDto;
import com.teamJ.budgetManagementApplication.budget.service.BudgetService;
import com.teamJ.budgetManagementApplication.common.dto.ApiResponseDto;
import com.teamJ.budgetManagementApplication.common.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budgets")
@Tag(name = "Budget API", description = "예산에 관한 API 정보를 담고 있습니다.")
public class BudgetController {
    private final BudgetService budgetService;

    @Operation(summary = "예산 설정", description = "사용자에게 필요한 정보를 받아 예산을 설정합니다.")
    @PostMapping
    public ResponseEntity<ApiResponseDto> createBudget(
            @Valid @RequestBody BudgetSetRequestDto budgetSetRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        budgetService.createBudget(budgetSetRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(
                new ApiResponseDto(HttpStatus.CREATED.value(), "예산 설정 완료")
        );
    }

    @Operation(summary = "예산 수정", description = "사용자에게 필요한 정보를 받아 예산을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        budgetService.updateBudget(id, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(
                new ApiResponseDto(HttpStatus.OK.value(), "예산 수정 완료")
        );
    }

    @Operation(summary = "예산 설계", description = "사용자의 총 예산을 받아 카테고리 별 예산을 추천합니다.")
    @PostMapping("/design")
    public ResponseEntity<List<BudgetDesignResponseDto>> designBudget(
            @RequestBody BudgetDesignRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(budgetService.designBudget(requestDto, userDetails.getUser()));
    }
}
