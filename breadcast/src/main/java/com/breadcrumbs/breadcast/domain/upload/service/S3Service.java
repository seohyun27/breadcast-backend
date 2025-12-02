package com.breadcrumbs.breadcast.domain.upload.service;

import com.breadcrumbs.breadcast.domain.upload.dto.PresignedUrlDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * AWS S3 파일 업로드 서비스 인터페이스
 */
public interface S3Service {
    /**
     * 단일 파일을 S3에 업로드 (백엔드를 통한 업로드)
     * @param file 업로드할 파일
     * @return 업로드된 파일의 URL
     */
    String uploadFile(MultipartFile file);

    /**
     * 여러 파일을 S3에 업로드 (백엔드를 통한 업로드)
     * @param files 업로드할 파일 리스트
     * @return 업로드된 파일들의 URL 리스트
     */
    List<String> uploadFiles(List<MultipartFile> files);

    /**
     * Presigned URL 생성 (프론트엔드가 직접 S3에 업로드하기 위한 URL)
     * @param fileName 파일명 (프론트엔드에서 전달받은 원본 파일명)
     * @param contentType 파일 타입 (예: image/jpeg, image/png)
     * @return PresignedUrlDto (presignedUrl과 finalUrl 포함)
     */
    PresignedUrlDto generatePresignedUrl(String fileName, String contentType);

    /**
     * S3에서 파일 삭제
     * @param fileUrl 삭제할 파일의 URL
     */
    void deleteFile(String fileUrl);
}
