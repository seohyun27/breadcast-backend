package com.breadcrumbs.breadcast.global.apiPayload.handler;

import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 1. GeneralException을 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(
            GeneralException ex
    ) {
        // GeneralException 발생 시 HTTP 500으로 고정 처리
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        // GeneralException에 저장된 메시지를 사용하여 응답
        return ResponseEntity.status(httpStatus)
                .body(ApiResponse.onFailure(
                        ex.getMessage(),
                        null    // 전송 data 없음
                ));
    }

    // 2. 그 외의 정의되지 않은 모든 예외를 처리
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