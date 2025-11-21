package com.breadcrumbs.breadcast.domain.member.repository;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existsByNickname(String nickname);
}
