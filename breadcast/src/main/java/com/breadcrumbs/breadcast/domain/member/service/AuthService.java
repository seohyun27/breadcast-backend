package com.breadcrumbs.breadcast.domain.member.service;

import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.SignupRequest;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import jakarta.servlet.http.HttpSession;

/**
 * 인증 서비스
 * - UserDetailsService 구현: Spring Security 인증에 필요한 사용자 정보 제공
 * - 비즈니스 로직: 회원가입, 로그인, 중복 확인 등
 * - @Lazy: AuthenticationManager의 순환 참조 방지 (일반적인 Spring 패턴)
 */
@Slf4j
@Service
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository;

    /**
     * 생성자 주입
     * @Lazy: AuthenticationManager는 UserDetailsService를 필요로 하므로,
     *        순환 참조 방지를 위해 지연 로딩 적용
     */
    public AuthService(
            MemberRepository memberRepository,
            @Lazy AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            SecurityContextRepository securityContextRepository
    ) {
        this.memberRepository = memberRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.securityContextRepository = securityContextRepository;
    }

    /**
     * 회원가입 메서드
     */
    @Transactional
    public MemberResponse signup(SignupRequest request) {
        // 1. 아이디 중복 검사는 @Valid의 @UniqueLoginId에서 처리됨
        // 2. 닉네임 중복 검사는 @Valid의 @UniqueNickname에서 처리됨

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 4. Member 엔티티 생성
        Member member = Member.createMember(
                request.getLoginId(),
                encodedPassword,
                request.getNickname()
        );

        // 5. MemberRepository에 저장
        memberRepository.save(member);

        // 6. MemberResponse 반환
        return MemberResponse.builder()
                .nickname(member.getNickname())
                .build();
    }

    /**
     * 로그인 메서드
     * Spring Security를 사용하여 인증을 수행
     * Spring Security 6.x부터는 SecurityContext를 명시적으로 저장해야 함
     */
    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        // 1. Spring Security 인증 수행
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getLoginId(),
                    loginRequest.getPassword()
            )
    );

        // 2. SecurityContext 생성 및 인증 정보 설정
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        // 3. ⭐ 세션 강제 생성 (이게 중요!)
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);  // 명시적으로 저장
    
        // 4. SecurityContext를 HTTP 세션에 저장
        securityContextRepository.saveContext(context, request, response);

        // 5. ⭐ 쿠키 명시적으로 설정
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId())
            .path("/")
            .httpOnly(true)
            .secure(true)           // HTTPS 환경
            .sameSite("None")       // Cross-origin 허용
            .maxAge(60 * 60 * 24 * 30)           // 30일
            .build();
    
        response.addHeader("Set-Cookie", cookie.toString());
    
        log.info("세션 생성 완료: sessionId={}", session.getId());
        log.info("쿠키 설정: {}", cookie.toString());

        // 6. 인증된 사용자 정보에서 닉네임 추출
        String loginId = authentication.getName();
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new GeneralException("사용자를 찾을 수 없습니다: " + loginId));

        // 7. MemberResponse 반환
        return MemberResponse.builder()
                .nickname(member.getNickname())
                .build();
    }

    /**
     * 아이디 중복 확인
     */
    @Transactional(readOnly = true)
    public boolean isLoginIdDuplicate(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }

    /**
     * 닉네임 중복 확인
     */
    @Transactional(readOnly = true)
    public boolean isNicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    /**
     * Spring Security가 인증 시 호출하는 메서드
     * UserDetailsService 인터페이스 구현
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // DB에서 loginId로 Member를 조회
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + loginId));

        // Member를 UserDetails로 변환하여 반환
        return new UserDetailsImpl(member);
    }
}