package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Member 기준으로 코스 리스트 조회
    List<Course> findByMember(Member member);
}
