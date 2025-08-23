package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.course.FavoriteCourse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
