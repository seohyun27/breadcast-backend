package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BakeryReviewRepository extends JpaRepository<BakeryReview, Long> {

    List<BakeryReview> findByMemberId(Long memId);

    int countByBakeryId(Long bakeryId);

    List<BakeryReview> findByBakeryId(Long bakeryId);
}