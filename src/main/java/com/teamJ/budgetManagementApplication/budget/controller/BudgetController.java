package com.teamJ.budgetManagementApplication.budget.controller;

import com.teamJ.budgetManagementApplication.budget.dto.BudgetSetRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Budget API", description = "예상 설정 API 정보를 담고 있습니다.")
public class BudgetController {
    private final BudgetService budgetService;

    @Operation(summary = "예산 설정", description = "사용자에게 필요한 정보를 받아 예산을 설정합니다.")
    @PostMapping("/budgets")
    public ResponseEntity<ApiResponseDto> createBudget(
            @Valid @RequestBody BudgetSetRequestDto budgetSetRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        budgetService.createBudget(budgetSetRequestDto, userDetails.getUser());
        return ResponseEntity.ok().body(
                new ApiResponseDto(HttpStatus.CREATED.value(), "예산 설정 완료")
        );
    }
}
