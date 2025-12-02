package com.breadcrumbs.breadcast.domain.upload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Presigned URL 생성 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Presigned URL 생성 요청")
public class PresignedUrlRequestDto {

    /**
     * 업로드할 파일 정보 리스트
     */
    @Schema(description = "업로드할 파일 정보 리스트", example = "[{\"fileName\": \"photo.jpg\", \"contentType\": \"image/jpeg\"}]")
    @NotEmpty(message = "파일 정보는 최소 1개 이상이어야 합니다.")
    private List<@Valid FileInfo> files;

    /**
     * 개별 파일 정보
     */
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "개별 파일 정보")
    public static class FileInfo {
        /**
         * 파일명 (확장자 포함)
         */
        @Schema(description = "파일명 (확장자 포함)", example = "photo.jpg")
        @NotNull(message = "파일명은 필수입니다.")
        private String fileName;

        /**
         * 파일 Content-Type (예: image/jpeg, image/png)
         */
        @Schema(description = "파일 Content-Type", example = "image/jpeg")
        @NotNull(message = "Content-Type은 필수입니다.")
        private String contentType;
    }
}
