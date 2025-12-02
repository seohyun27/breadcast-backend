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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
     * AuthService에서 주입받아 사용
     * Spring Security가 UserDetailsService Bean을 자동으로 찾아 인증에 사용
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
                // 1. 시큐리티 필터 체인에서 CORS 설정을 활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 1. CSRF 보호 비활성화 (REST API이므로)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. HTTP 기본 인증(Basic Auth) 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)

                // 3. 폼 기반 로그인 비활성화 (우리가 /login API를 직접 만듦)
                .formLogin(AbstractHttpConfigurer::disable)

                // 4. (중요!!!!!) URL별 접근 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // 인증 없이 사용 가능한 경로들
                        .requestMatchers("/auth/**").permitAll() // 로그인 회원 가입을 위한 경로
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger UI

                        // 내 정보 관련 경로는 모두 인증 필요
                        .requestMatchers("/api/members/me/**").authenticated()

                        // GET 요청은 모두 허용 (조회는 로그인 없이 가능)
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/**").permitAll()

                        // 생성/수정/삭제는 인증 필요
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/**").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.PATCH, "/api/**").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/**").authenticated()
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/**").authenticated()

                        // 나머지 모든 요청은 인증(로그인)이 필요함
                        .anyRequest().authenticated()
                )

                // 5. 로그아웃 설정
                .logout(logout -> logout
                        // 클라이언트가 호출할 로그아웃 URL
                        .logoutUrl("/auth/logout")

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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 허용할 오리진 목록 (3000, 5173 포함)
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));

        // 허용할 HTTP 메서드 목록
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // 인증 정보(쿠키 등) 허용 여부
        configuration.setAllowCredentials(true);

        // 허용할 헤더 목록
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
