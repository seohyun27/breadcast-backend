package com.breadcrumbs.breadcast.global.apiPayload.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final String message; // 에러 메시지를 직접 저장

    public GeneralException(String message) {
        super(message); // RuntimeException의 메시지로도 전달
        this.message = message;
    }
}
