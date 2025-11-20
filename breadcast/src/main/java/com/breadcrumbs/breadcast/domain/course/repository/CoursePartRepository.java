package com.breadcrumbs.breadcast.domain.course.repository;

import com.breadcrumbs.breadcast.domain.course.CoursePart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePartRepository extends JpaRepository<CoursePart, Long> {

    // courseId에 해당하는 모든 CoursePart 엔티티를 DB에서 삭제
    void deleteAllByCourseId(Long courseId);

    // courseId에 포함된 모든 CoursePart 엔티티를 찾아 리스트로 반환합니다.
    List<CoursePart> findByCourseId(Long courseId);

}
