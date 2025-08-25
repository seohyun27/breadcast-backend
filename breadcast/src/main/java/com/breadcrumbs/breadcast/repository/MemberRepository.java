package com.breadcrumbs.breadcast.repository;

import com.breadcrumbs.breadcast.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 해당 닉네임이 DB 내에 이미 존재하고 있는지를 검사하는 메소드
    boolean existsByNickname(String nickname);
}
