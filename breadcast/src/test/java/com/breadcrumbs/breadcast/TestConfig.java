package com.breadcrumbs.breadcast;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

/**
 * 테스트용 설정 클래스
 * 테스트 환경에서 필요한 Mock 빈들을 제공합니다.
 */
@TestConfiguration
@Profile("test")
public class TestConfig {

    /**
     * 테스트용 Mock AmazonS3Client 빈
     * 실제 S3 연결 없이 테스트를 실행할 수 있도록 합니다.
     */
    @Bean
    @Primary
    public AmazonS3Client amazonS3Client() {
        return mock(AmazonS3Client.class);
    }
}

