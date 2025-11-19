package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    // courseId에 포함된 모든 CourseReview 엔티티를 찾아 리스트로 반환
    List<CourseReview> findByCourseId(Long courseId);

    // courseId에 해당하는 모든 CourseReview 엔티티를 DB에서 삭제
    void deleteAllByCourseId(Long courseId);


    List<CourseReview> findByMemberId(Long memId);

}
