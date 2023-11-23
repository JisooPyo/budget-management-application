package com.teamJ.budgetManagementApplication.expense.controller;

import com.teamJ.budgetManagementApplication.common.dto.ApiResponseDto;
import com.teamJ.budgetManagementApplication.common.security.UserDetailsImpl;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.expense.service.ExpenseService;
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
@Tag(name = "지출 API", description = "지출에 관한 API 정보를 담고 있습니다.")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Operation(summary = "지출 생성", description = "사용자에게 필요한 정보를 받아 지출을 기록합니다.")
    @PostMapping("/expenses")
    public ResponseEntity<ApiResponseDto> createExpense(
            @Valid @RequestBody ExpenseCreateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        expenseService.createExpense(requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(
                new ApiResponseDto(HttpStatus.CREATED.value(), "지출 기록 완료")
        );
    }
}
