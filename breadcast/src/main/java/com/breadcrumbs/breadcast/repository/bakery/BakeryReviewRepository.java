package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
