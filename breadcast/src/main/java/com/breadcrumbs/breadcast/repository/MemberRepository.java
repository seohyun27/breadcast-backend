package com.breadcrumbs.breadcast.repository;

import com.breadcrumbs.breadcast.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByloginId(String loginId);

    boolean existsByNickname(String nickname);
}
