package com.breadcrumbs.breadcast.domain.member.service;

import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService { // UserDetailsService 구현

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;

    /**
     * 로그인 메서드
     * Spring Security를 사용하여 인증을 수행하고 세션을 생성
     */
    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest loginRequest, HttpServletRequest httpRequest) {
        // 1. Spring Security 인증 수행
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );

        // 2. SecurityContext 생성 및 인증 정보 설정
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        // 3. 세션 생성 및 SecurityContext 저장
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        // 4. 인증된 사용자 정보에서 닉네임 추출
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String nickname = userDetails.getMember().getNickname();

        // 5. MemberResponse 반환
        return MemberResponse.builder()
                .nickname(nickname)
                .build();
    }


    /**
     * Spring Security가 내부적으로 호출할 메서드
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // DB에서 loginId로 Member를 조회
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + loginId));

        // 찾아낸 Member를 어댑터(UserDetailsImpl)로 감싸서 반환
        return new UserDetailsImpl(member);
    }
}