package com.breadcrumbs.breadcast.global.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security를 활성화
public class SecurityConfig {

    /**
     * 비밀번호 암호화 도구
     * BCryptPasswordEncoder를 빈으로 등록
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증 관리자 (AuthenticationManager)
     * AuthService의 login() 메서드에서 주입받아 사용
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Spring Security의 핵심
     * HTTP 보안 규칙 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보호 비활성화 (REST API이므로)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. HTTP 기본 인증(Basic Auth) 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)

                // 3. 폼 기반 로그인 비활성화 (우리가 /login API를 직접 만듦)
                .formLogin(AbstractHttpConfigurer::disable)

                // 4. (중요!!!!!) URL별 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // "/api/auth/**" 경로는 모두 허용 (로그인, 회원가입 등)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 나머지 모든 요청은 인증(로그인)이 필요함
                        .anyRequest().authenticated()
                )

                // 5. 로그아웃 설정
                .logout(logout -> logout
                        // 클라이언트가 호출할 로그아웃 URL
                        .logoutUrl("/api/auth/logout")

                        // 로그아웃 성공 시 반환할 HTTP 상태 코드 (200 OK)
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_OK)
                        )

                        // 세션 무효화
                        .invalidateHttpSession(true)

                        // JSESSIONID 쿠키 삭제
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}
