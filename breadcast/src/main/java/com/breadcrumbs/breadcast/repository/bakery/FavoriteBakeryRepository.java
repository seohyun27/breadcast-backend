package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import com.breadcrumbs.breadcast.domain.course.Course;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FavoriteBakeryRepository {

    private final EntityManager em;

    public void save(FavoriteBakery favoriteBakery){
        em.persist(favoriteBakery);
    }

    public void delete(Long id) {
        FavoriteBakery favoriteBakery = em.find(FavoriteBakery.class, id);
        if (favoriteBakery != null) em.remove(favoriteBakery);
    }

    public FavoriteBakery findOne(Long id){
        return em.find(FavoriteBakery.class, id);
    }

    // Member 기준으로 즐겨찾기 빵집 리스트 조회
    public List<FavoriteBakery> findByMember(Member member) {
        return em.createQuery("select fb from FavoriteBakery fb where fb.member = :member",
                        FavoriteBakery.class)
                .setParameter("member", member)
                .getResultList();
    }

}
