package com.breadcrumbs.breadcast.repository.menu;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.menu.MenuReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuReviewRepository {

    private final EntityManager em;

    public void save(MenuReview menuReview){
        em.persist(menuReview);
    }

    public void delete(Long id) {
        MenuReview menuReview = em.find(MenuReview.class, id);
        if (menuReview != null) em.remove(menuReview);
    }

    public MenuReview findOne(Long id){
        return em.find(MenuReview.class, id);
    }

    // Member 기준으로 메뉴 리뷰 리스트 조회
    public List<MenuReview> findByMember(Member member) {
        return em.createQuery("select mr from MenuReview mr where mr.member = :member ORDER BY mr.date DESC",
                        MenuReview.class)
                .setParameter("member", member)   // 파라미터 설정
                .getResultList();
    }
}
