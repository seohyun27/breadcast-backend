package com.breadcrumbs.breadcast.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "message", "data"})
public class ApiResponse<T> {

    @JsonProperty("success")
    private final Boolean success;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private T data;

    // 성공한 경우 (결과 포함)
    // 컨트롤러에서 직접 사용
    public static <T> ApiResponse<T> onSuccess(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    // 실패한 경우 (결과 포함)
    // 내가 예외를 던지면 에러 핸들러에서 나 대신 사용
    public static <T> ApiResponse<T> onFailure(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }
}
