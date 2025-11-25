package com.breadcrumbs.breadcast.domain.member.controller;

import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.SignupRequest;
import com.breadcrumbs.breadcast.domain.member.service.AuthService;
import com.breadcrumbs.breadcast.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 모든 컨트롤러 클래스에서
 * 각 메소드별 api 매핑 파트 포함하지 않음
 * 인자값 어노테이션 처리 없음
 * 필요하다면 @RequestMapping("/api/v1/...") 사용해 공통 경로 지정할 수 있음
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody @Valid SignupRequest request){
        log.info("회원가입 시도: {}", request.getLoginId());
        try {
            MemberResponse response = memberService.addMember(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.error("회원가입 실패: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(
            @RequestBody @Valid LoginRequest request,
            HttpServletRequest httpRequest) {
        log.info("로그인 시도: {}", request.getLoginId());

        try {
            // AuthService에 인증 로직 위임
            MemberResponse response = authService.login(request, httpRequest);
            log.info("로그인 성공: {}", request.getLoginId());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("로그인 실패: {}", e.getMessage());
            throw new IllegalArgumentException("로그인에 실패했습니다: " + e.getMessage());
        }
    }

}
