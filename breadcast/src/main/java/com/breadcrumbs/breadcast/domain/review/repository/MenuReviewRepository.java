package com.breadcrumbs.breadcast.repository.menu;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.menu.MenuReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuReviewRepository extends JpaRepository<MenuReview, Long> {

    // Member 기준으로 메뉴 리뷰 리스트 조회
    List<MenuReview> findByMemberId(Long memId);

    // 특정 메뉴에 대한 모든 리뷰 목록을 조회합니다.
    List<MenuReview> findByMenuId(Long menuId);

    // 특정 메뉴의 리뷰 수를 계산합니다.
    long countByMenuId(Long menuId);

}
