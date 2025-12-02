package com.breadcrumbs.breadcast.domain.upload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Presigned URL 생성 응답 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Presigned URL 생성 응답")
public class PresignedUrlResponseDto {
    /**
     * 생성된 Presigned URL 리스트
     */
    @Schema(description = "생성된 Presigned URL 리스트")
    private List<PresignedUrlDto> urls;
}
