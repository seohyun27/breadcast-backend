package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BakeryReviewRepository {

    private final EntityManager em;

    public void save(BakeryReview bakeryReview){
        em.persist(bakeryReview);
    }

    public void delete(Long id) {
        BakeryReview bakeryReview = em.find(BakeryReview.class, id);
        if (bakeryReview != null) em.remove(bakeryReview);
    }

    public BakeryReview findOne(Long id){
        return em.find(BakeryReview.class, id);
    }

    // Member 기준으로 빵집 리뷰 리스트 조회
    public List<BakeryReview> findByMember(Member member) {
        return em.createQuery("select br from BakeryReview br where br.member = :member",
                        BakeryReview.class)
                .setParameter("member", member)
                .getResultList();
    }
}
