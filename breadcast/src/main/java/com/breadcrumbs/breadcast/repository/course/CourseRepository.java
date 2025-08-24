package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.Course;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    // Member 기준으로 코스 리스트 조회
    public List<Course> findByMember(Member member) {
        return em.createQuery("select c from Course c where c.member = :member",
                        Course.class)
                .setParameter("member", member)
                .getResultList();
    }

}
