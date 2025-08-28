package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {

    // Member 기준으로 즐겨찾기 코스 리스트 조회
    List<FavoriteCourse> findByMember(Member member);

    // courseId에 해당하는 좋아요의 개수를 세어 long 타입으로 반환
    public long countByCourseId(Long courseId);

}
