package com.breadcrumbs.breadcast.domain.member.controller;

import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.SignupRequest;
import com.breadcrumbs.breadcast.domain.member.service.AuthService;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final AuthService authService;

    @GetMapping("/validation/login-id")
    public ApiResponse<Void> checkLoginId(@RequestParam String loginId) {
        log.info("아이디 중복 확인 요청: {}", loginId);

        if (authService.isLoginIdDuplicate(loginId)) {
            return ApiResponse.onFailure("이미 사용중인 아이디가 있습니다.", null);
        } else {
            return ApiResponse.onSuccess("사용 가능한 아이디입니다.", null);
        }
    }

    @GetMapping("/validation/nickname")
    public ApiResponse<Void> checkNickname(@RequestParam String nickname) {
        log.info("닉네임 중복 확인 요청: {}", nickname);

        if (authService.isNicknameDuplicate(nickname)) {
            return ApiResponse.onFailure("이미 사용중인 닉네임이 있습니다.", null);
        } else {
            return ApiResponse.onSuccess("사용 가능한 닉네임입니다.", null);
        }
    }

    @PostMapping("/signup")
    public ApiResponse<MemberResponse> signup(@RequestBody @Valid SignupRequest request){
        log.info("회원가입 시도: {}", request.getLoginId());
        MemberResponse response = authService.signup(request);
        return ApiResponse.onSuccess("회원가입에 성공하였습니다.", response);
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponse> login(
            @RequestBody @Valid LoginRequest request,
            HttpServletRequest httpRequest) {
        log.info("로그인 시도: {}", request.getLoginId());
        MemberResponse response = authService.login(request, httpRequest);
        log.info("로그인 성공: {}", request.getLoginId());
        return ApiResponse.onSuccess("로그인에 성공하였습니다.", response);
    }

}
