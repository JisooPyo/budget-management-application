package com.teamJ.budgetManagementApplication.user.controller;

import com.teamJ.budgetManagementApplication.common.dto.ApiResponseDto;
import com.teamJ.budgetManagementApplication.user.dto.SignupRequestDto;
import com.teamJ.budgetManagementApplication.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "사용자 API", description = "사용자와 관련된 API 정보를 담고 있습니다.")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입",
            description = "DTO를 통해 유효성 검사 후 통과 시 회원가입 시키고 성공 메시지를 반환합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok().body(
                new ApiResponseDto(HttpStatus.CREATED.value(), "회원 가입 성공")
        );
    }
}
