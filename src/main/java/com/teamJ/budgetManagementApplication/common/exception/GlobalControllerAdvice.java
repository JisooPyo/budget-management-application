package com.teamJ.budgetManagementApplication.common.exception;

import com.teamJ.budgetManagementApplication.common.dto.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResponseDto> handlerCustomException(CustomException e) {
        ApiResponseDto restApiException =
                new ApiResponseDto(e.getErrorCode().getErrorCode(), e.getErrorCode().getErrorMessage());
        return ResponseEntity.status(restApiException.getStatusCode()).body(restApiException);
    }

    // validation에서 예외가 생겼을 때는 여기서 잡아줍니다.
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseDto> handlerValidationException(MethodArgumentNotValidException ex) {
        // Validation 예외처리
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : ex.getFieldErrors()) {
            log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            errorMessage.append(fieldError.getField())
                    .append(" 필드 : ")
                    .append(fieldError.getDefaultMessage())
                    .append(" ");
        }

        return ResponseEntity.badRequest().body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), errorMessage.toString()));
    }

    // RequestParam에 'required = true'인 매개변수가 주어지지 않을 시 발생하는 예외 처리
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ApiResponseDto> handlerMissingParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body(
                new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}
