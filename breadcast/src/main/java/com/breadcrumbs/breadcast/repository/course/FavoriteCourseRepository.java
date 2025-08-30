package com.breadcrumbs.breadcast.repository.course;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.course.FavoriteCourse;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {

    List<FavoriteCourse> findByMemberId(Long memberId);

    // courseId에 해당하는 좋아요의 개수를 세어 long 타입으로 반환
    public long countByCourseId(Long courseId);

    boolean existsByMemberIdAndCourseId(Long memberId, Long courseId) ;

    void deleteByMemberIdAndCourseId(Long memId, Long courseId);
}
