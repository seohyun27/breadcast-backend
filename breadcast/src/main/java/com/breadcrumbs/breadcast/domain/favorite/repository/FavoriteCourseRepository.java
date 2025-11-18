package com.breadcrumbs.breadcast.domain.favorite.repository;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.course.entity.FavoriteCourse;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {

    List<FavoriteCourse> findByMemberId(Long memberId);

    // courseId에 해당하는 좋아요의 개수를 세어 long 타입으로 반환
    long countByCourseId(Long courseId);

    boolean existsByMemberIdAndCourseId(Long memberId, Long courseId) ;

    void deleteByMemberIdAndCourseId(Long memId, Long courseId);

    void deleteAllByCourseId(Long courseId);
}
