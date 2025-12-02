package com.breadcrumbs.breadcast.domain.upload.controller;

import com.breadcrumbs.breadcast.domain.upload.dto.PresignedUrlDto;
import com.breadcrumbs.breadcast.domain.upload.dto.PresignedUrlRequestDto;
import com.breadcrumbs.breadcast.domain.upload.dto.PresignedUrlResponseDto;
import com.breadcrumbs.breadcast.domain.upload.service.S3Service;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 파일 업로드 관련 API Controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
@Tag(name = "Upload", description = "파일 업로드 API")
public class UploadController {

    private final S3Service s3Service;

    /**
     * Presigned URL 생성 API
     * 프론트엔드가 S3로 직접 파일을 업로드하기 위한 임시 URL을 생성합니다.
     *
     * @param request 파일 정보 리스트 (파일명, Content-Type)
     * @return Presigned URL과 최종 URL 리스트
     */
    @PostMapping("/presigned-urls")
    @Operation(summary = "Presigned URL 생성",
               description = "프론트엔드가 S3로 직접 파일을 업로드하기 위한 Presigned URL을 생성합니다. " +
                           "생성된 URL은 5분간 유효하며, PUT 메서드로 파일을 업로드할 수 있습니다.")
    public ApiResponse<PresignedUrlResponseDto> generatePresignedUrls(
            @RequestBody @Valid PresignedUrlRequestDto request) {

        log.info("Presigned URL 생성 요청: {} 개의 파일", request.getFiles().size());

        // 각 파일에 대해 Presigned URL 생성
        List<PresignedUrlDto> presignedUrls = request.getFiles().stream()
                .map(fileInfo -> s3Service.generatePresignedUrl(
                        fileInfo.getFileName(),
                        fileInfo.getContentType()
                ))
                .collect(Collectors.toList());

        PresignedUrlResponseDto response = PresignedUrlResponseDto.builder()
                .urls(presignedUrls)
                .build();

        log.info("Presigned URL 생성 완료: {} 개", presignedUrls.size());

        return ApiResponse.onSuccess("Presigned URL 생성에 성공하였습니다.", response);
    }
}
