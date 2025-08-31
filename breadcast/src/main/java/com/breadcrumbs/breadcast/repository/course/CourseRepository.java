package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByMemberId(Long memId);


    // 제목 또는 키워드에 특정 문자열이 포함된 코스 목록을 검색합니다.
    // Pageable 객체를 사용하여 페이징 및 정렬을 함께 처리합니다.
    Page<Course> findByTitleContainingOrKeywordContaining(String title, String keyword, Pageable pageable);

}
