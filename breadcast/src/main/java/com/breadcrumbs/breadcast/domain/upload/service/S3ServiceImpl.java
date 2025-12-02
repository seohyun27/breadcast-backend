package com.breadcrumbs.breadcast.domain.upload.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.breadcrumbs.breadcast.domain.upload.dto.PresignedUrlDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * AWS S3 파일 업로드 서비스 구현체
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    private static final int PRESIGNED_URL_EXPIRY_MINUTES = 5; // Presigned URL 만료 시간 (5분)

    /**
     * BASE_URL 생성 (버킷 이름과 리전 기반)
     */
    private String getBaseUrl() {
        return String.format("https://%s.s3.%s.amazonaws.com", bucketName, region);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 비어 있습니다.");
        }

        String uniqueFileName = generateUniqueFileName("uploads", file.getOriginalFilename());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, uniqueFileName, inputStream, metadata);
        } catch (IOException e) {
            log.error("S3 업로드 실패 - fileName={}", file.getOriginalFilename(), e);
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }

        String finalUrl = getBaseUrl() + "/" + uniqueFileName;
        log.debug("S3 업로드 완료 - key={}, url={}", uniqueFileName, finalUrl);
        return finalUrl;
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) {
        return files.stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @Override
    public PresignedUrlDto generatePresignedUrl(String fileName, String contentType) {
        // 고유한 파일명 생성
        String uniqueFileName = generateUniqueFileName("uploads", fileName);

        // 만료 시간 설정 (현재 시간 + 5분)
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000L * 60 * PRESIGNED_URL_EXPIRY_MINUTES;
        expiration.setTime(expTimeMillis);

        // Presigned URL 생성 요청
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                bucketName,
                uniqueFileName
        )
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration)
                .withContentType(contentType);

        // Presigned URL 생성
        URL presignedUrl = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);

        // 최종 URL 생성
        String finalUrl = getBaseUrl() + "/" + uniqueFileName;

        log.debug("Presigned URL 생성 완료: fileName={}, contentType={}", uniqueFileName, contentType);

        return PresignedUrlDto.builder()
                .fileName(uniqueFileName)
                .presignedUrl(presignedUrl.toString())
                .finalUrl(finalUrl)
                .build();
    }

    @Override
    public void deleteFile(String fileUrl) {
        try {
            // URL에서 S3 키 추출
            String key = fileUrl.substring(fileUrl.indexOf(bucketName) + bucketName.length() + 1);
            amazonS3Client.deleteObject(bucketName, key);
            log.debug("S3 파일 삭제 완료 - key={}", key);
        } catch (Exception e) {
            log.error("S3 파일 삭제 실패 - fileUrl={}", fileUrl, e);
            throw new RuntimeException("파일 삭제 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 고유한 파일명 생성 (타임스탬프 + UUID + 원본 파일명)
     */
    private String generateUniqueFileName(String folder, String originalFileName) {
        String safeFileName = originalFileName != null ? originalFileName : "uploaded";
        String extension = "";
        int lastDotIndex = safeFileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = safeFileName.substring(lastDotIndex);
            safeFileName = safeFileName.substring(0, lastDotIndex);
        }

        // 파일명에서 안전하지 않은 문자 제거
        safeFileName = safeFileName.replaceAll("[^a-zA-Z0-9-_]", "_");

        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        String uuid = UUID.randomUUID().toString().substring(0, 8);

        return String.format("%s/%s_%s_%s%s", folder, timestamp, uuid, safeFileName, extension);
    }
}
