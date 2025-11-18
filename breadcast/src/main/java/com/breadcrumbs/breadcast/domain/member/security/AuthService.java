package com.breadcrumbs.breadcast.domain.member.security;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.dto.LoginRequest;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService { // UserDetailsService 구현

    // (Member를 찾아올 Repository)
    private final MemberRepository memberRepository;

    // (Spring Security가 제공하는 인증 관리자 - Config 파일에서 Bean으로 등록해야 함)
    private final AuthenticationManager authenticationManager;
    // (비밀번호 암호화 - Config 파일에서 Bean으로 등록해야 함)
    // private final PasswordEncoder passwordEncoder;

    /**
     * 컨트롤러가 호출할 로그인 메서드
     * 인증은 이미 컨트롤러에서 완료되었으므로, SecurityContext에서 인증 정보를 가져와 응답을 생성합니다.
     */
    @Transactional(readOnly = true)
    public MemberResponse login(LoginRequest loginRequest) {
        // SecurityContext에서 인증 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("인증되지 않은 사용자입니다");
        }

        // 인증 객체에서 UserDetailsImpl을 꺼냄
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // UserDetailsImpl에서 Member 객체의 닉네임을 꺼냄
        String nickname = userDetails.getMember().getNickname();

        // MemberResponse DTO에 닉네임을 담아 반환
        return new MemberResponse(nickname);
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