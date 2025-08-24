package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import com.breadcrumbs.breadcast.domain.course.FavoriteCourse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FavoriteCourseRepository {

    private final EntityManager em;

    public void save(FavoriteCourse favoriteCourse){
        em.persist(favoriteCourse);
    }

    public void delete(Long id) {
        FavoriteCourse favoriteCourse = em.find(FavoriteCourse.class, id);
        if (favoriteCourse != null) em.remove(favoriteCourse);
    }

    public FavoriteCourse findOne(Long id){
        return em.find(FavoriteCourse.class, id);
    }

    // Member 기준으로 즐겨찾기 코스 리스트 조회
    public List<FavoriteCourse> findByMember(Member member) {
        return em.createQuery("select fc from FavoriteCourse fc where fc.member = :member",
                        FavoriteCourse.class)
                .setParameter("member", member)
                .getResultList();
    }

}
