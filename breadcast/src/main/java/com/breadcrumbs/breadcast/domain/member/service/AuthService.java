package com.breadcrumbs.breadcast.domain.member.service;

import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.SignupRequest;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService { // UserDetailsService 구현

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

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
     * Spring Security가 내부적으로 호출할 메서드
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // DB에서 loginId로 Member를 조회
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new GeneralException("사용자를 찾을 수 없습니다: " + loginId));

        // 찾아낸 Member를 어댑터(UserDetailsImpl)로 감싸서 반환
        return new UserDetailsImpl(member);
    }
}