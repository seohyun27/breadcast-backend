package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.course.CourseReview;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
