package com.breadcrumbs.breadcast.global.apiPayload.handler;

import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 1. GeneralException을 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(
            GeneralException ex
    ) {
        // GeneralException 발생 시 HTTP 400 Bad Request로 처리
        // 사용자를 찾을 수 없거나 잘못된 요청의 경우 400번대 오류가 적절함
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // GeneralException에 저장된 메시지를 사용하여 응답
        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.onFailure(
                        ex.getMessage(),
                        null    // 전송 data 없음
                ));
    }

    // 2. Spring Security 인증 실패 예외 처리 (로그인 실패 등)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(
            AuthenticationException ex
    ) {
        // HTTP 400 Bad Request로 처리
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 인증 실패 메시지 설정
        String errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";
        if (ex instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 올바르지 않습니다.";
        } else if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
            errorMessage = ex.getMessage();
        }

        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.onFailure(
                        errorMessage,
                        null
                ));
    }

    // 3. @Valid 검증 실패 시 발생하는 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        // HTTP 400 Bad Request로 처리
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 첫 번째 검증 오류 메시지를 가져옴
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("입력값이 유효하지 않습니다.");

        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.onFailure(
                        errorMessage,
                        null
                ));
    }

    // 4. 그 외의 정의되지 않은 모든 예외를 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleAllOtherExceptions(
            Exception ex
    ) {
        // HTTP 500으로 고정 처리
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        // GeneralException을 제외한 모든 예외는 일반적인 500 에러 메시지를 사용
        String defaultErrorMessage = "서버 내부 오류가 발생했습니다.";

        // 실제 예외 메시지는 디버깅을 위해 data 필드에 반환
        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.onFailure(
                        defaultErrorMessage,
                        ex.getMessage() // 실제 예외 메시지 (디버깅 정보)
                ));
    }
}