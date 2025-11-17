package com.breadcrumbs.breadcast.s3;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    // application.yml에서 설정한 값들을 Java 변수로 가져옵니다.
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        // 1. yml에서 가져온 accessKey와 secretKey로 AWS 자격 증명(Credential) 객체를 만듭니다.
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // 2. 자격 증명 객체와 리전 정보를 사용해 S3 클라이언트 빌더(Builder)를 구성합니다.
        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withRegion(region) // 리전 설정
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) // 자격 증명 설정
                .build(); // S3 클라이언트 객체 생성
    }
}