package com.breadcrumbs.breadcast.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}") // application.yml의 bucket 이름
    private String bucket;

    /**
     * S3로 파일을 업로드합니다.
     *
     * @param file    업로드할 파일 (MultipartFile)
     * @param dirName S3 버킷 내부의 디렉토리 이름 (예: "review", "bakery")
     * @return 업로드된 파일의 S3 URL
     * @throws IOException
     */
    public String upload(MultipartFile file, String dirName) throws IOException {

        // 1. 파일명 중복을 피하기 위해 UUID로 고유한 파일명 생성
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String s3FileName = dirName + "/" + UUID.randomUUID().toString().substring(0, 10) + originalFileName;

        // 2. 파일 메타데이터 설정 (파일 크기, 콘텐츠 타입 등)
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // 3. S3에 파일 업로드 (putObject)
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3FileName, file.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)); // PublicRead로 설정해야 외부에서 URL로 접근 가능

        // 4. 업로드된 파일의 S3 URL 반환
        return amazonS3Client.getUrl(bucket, s3FileName).toString();
    }
}