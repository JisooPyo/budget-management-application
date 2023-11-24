package com.teamJ.budgetManagementApplication.expense.controller;

import com.teamJ.budgetManagementApplication.common.dto.ApiResponseDto;
import com.teamJ.budgetManagementApplication.common.security.UserDetailsImpl;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseCreateRequestDto;
import com.teamJ.budgetManagementApplication.expense.dto.ExpenseListResponseDto;
import com.teamJ.budgetManagementApplication.expense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "지출 목록 읽기",
            description = "필수적으로 기간으로 조회하며 모든 지출 합계, 카테고리 별 지출 합계를 같이 반환홥니다.")
    @GetMapping("/expenses")
    public ResponseEntity<ExpenseListResponseDto> getAllExpenses(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "min", required = false) Integer min,
            @RequestParam(name = "max", required = false) Integer max,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(
                expenseService.getAllExpenses(start, end, categoryId, min, max, userDetails.getUser())
        );
    }
}
