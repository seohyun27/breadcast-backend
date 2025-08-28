package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    // Member 기준으로 코스 리뷰 리스트 조회
    List<CourseReview> findByMember(Member member);

    // courseId에 포함된 모든 CourseReview 엔티티를 찾아 리스트로 반환
    public List<CourseReview> findByCourseId(Long courseId);

}
