package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.course.CoursePart;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CoursePartRepository {

    private final EntityManager em;

    public void save(CoursePart coursePart){
        em.persist(coursePart);
    }

    public void delete(Long id) {
        CoursePart coursePart = em.find(CoursePart.class, id);
        if (coursePart != null) em.remove(coursePart);
    }

    public CoursePart findOne(Long id){
        return em.find(CoursePart.class, id);
    }

}
