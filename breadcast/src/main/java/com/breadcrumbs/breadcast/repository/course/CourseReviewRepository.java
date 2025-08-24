package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseReviewRepository {

    private final EntityManager em;

    public void save(CourseReview courseReview){
        em.persist(courseReview);
    }

    public void delete(Long id) {
        CourseReview courseReview = em.find(CourseReview.class, id);
        if (courseReview != null) em.remove(courseReview);
    }

    public CourseReview findOne(Long id){
        return em.find(CourseReview.class, id);
    }

    // Member 기준으로 코스 리뷰 리스트 조회
    public List<CourseReview> findByMember(Member member) {
        return em.createQuery("select cr from CourseReview cr where cr.member = :member ORDER BY cr.date DESC",
                        CourseReview.class)
                .setParameter("member", member)
                .getResultList();
    }
}
