package com.breadcrumbs.breadcast.repository;

import com.breadcrumbs.breadcast.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor // final이 붙은 변수들을 포함해 기본 생성자를 만들어줌
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        if (member != null) em.remove(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    /// 구현 중
    public Optional<Member> findById(Long memId) {
        return null;
    }

    /// 구현 중
    public boolean existsNickname(String newNickname) {
        return true;
    }
}
