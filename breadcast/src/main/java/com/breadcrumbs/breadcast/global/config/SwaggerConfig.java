package com.breadcrumbs.breadcast.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme cookieAuth = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.COOKIE)
                .name("JSESSIONID")
                .description("세션 쿠키 (로그인 후 자동 설정됨)");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("cookieAuth");

        return new OpenAPI()
                .info(new Info()
                        .title("BreadCast API")
                        .description("빵집 리뷰 및 빵 여행 루트 공유 플랫폼 API 문서")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("BreadCast Team")
                                .email("breadcast@example.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("로컬 개발 서버")
                ))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("cookieAuth", cookieAuth))
                .addSecurityItem(securityRequirement);
    }
}
