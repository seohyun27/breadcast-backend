package com.breadcrumbs.breadcast.repository.menu;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.menu.MenuReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuReviewRepository extends JpaRepository<MenuReview, Long> {

    // Member 기준으로 메뉴 리뷰 리스트 조회
    List<MenuReview> findByMember(Member member);

}
