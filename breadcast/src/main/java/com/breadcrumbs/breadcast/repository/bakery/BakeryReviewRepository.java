package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BakeryReviewRepository extends JpaRepository<BakeryReview, Long> {

    // Member 기준으로 빵집 리뷰 리스트 조회
    List<BakeryReview> findByMemberOrderByDateDesc(Member member);
}