package com.breadcrumbs.breadcast.domain.upload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Presigned URL 정보를 담는 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Presigned URL 정보")
public class PresignedUrlDto {
    /**
     * S3에 저장될 고유한 파일명 (UUID 포함)
     */
    @Schema(description = "S3에 저장될 고유한 파일명", example = "uploads/1701234567890_a1b2c3d4_photo.jpg")
    private String fileName;

    /**
     * 프론트엔드가 PUT 요청으로 파일을 업로드할 수 있는 임시 URL (5분간 유효)
     */
    @Schema(description = "파일 업로드용 Presigned URL (5분간 유효)")
    private String presignedUrl;

    /**
     * 업로드 완료 후 파일에 접근할 수 있는 최종 URL
     */
    @Schema(description = "업로드 완료 후 파일 접근 URL", example = "https://breadcast-image-bucket.s3.ap-northeast-2.amazonaws.com/uploads/1701234567890_a1b2c3d4_photo.jpg")
    private String finalUrl;
}
