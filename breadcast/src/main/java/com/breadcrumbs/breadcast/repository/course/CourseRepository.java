package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.course.Course;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager em;

    public void save(Course course){
        em.persist(course);
    }

    public void delete(Long id) {
        Course course = em.find(Course.class, id);
        if (course != null) em.remove(course);
    }

    public Course findOne(Long id){
        return em.find(Course.class, id);
    }

}
