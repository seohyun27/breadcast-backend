package com.breadcrumbs.breadcast.global.security;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections; // 권한이 없으므로 빈 리스트 반환하기 위한 import

/**
 * '어댑터' 클래스
 * Member 엔티티를 Spring Security가 이해하는 UserDetails 객체로 변환
 */

public class UserDetailsImpl implements UserDetails {

    // Member 엔티티를 필드로 사용 (Composition)
    private final Member member;

    public UserDetailsImpl(Member member) {
        this.member = member;
    }

    // 컨트롤러에서 ID를 꺼내 서비스로 넘겨주기 위한 메소드
    public long getUserId() {
        return member.getId();
    }

    // 컨트롤러에서 멤버를 반환하는 메소드
    public Member getMember() {
        return this.member;
    }

    /// UserDetails 인터페이스를 구현하기 위한 필수 구현 메소드들 ///

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member 엔티티에 'Role' 필드가 없으므로, 지금은 권한이 없는 것으로 설정
        // 만약 Role(권한) 클래스를 만들게 된다면 해당 메소드에서 반환할 수 있다
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // 8. Member의 password 반환
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // 9. Member의 loginId를 UserDetails의 'username'으로 사용
        return member.getLoginId();
    }

    // --- 계정 상태 (Member의 'active' 필드 활용) ---

    @Override
    public boolean isEnabled() {
        // Member의 'active' 필드를 계정 활성화 여부로 사용하기 위해 필요한 메소드
        return member.isActive();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 별도 필드가 없으므로 true 처리
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 별도 필드가 없으므로 true 처리
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 별도 필드가 없으므로 true 처리
        return true;
    }
}