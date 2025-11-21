package com.breadcrumbs.breadcast.domain.review.repository;

import com.breadcrumbs.breadcast.domain.review.entity.BakeryReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BakeryReviewRepository extends JpaRepository<BakeryReview, Long> {

    List<BakeryReview> findByMemberId(Long memId);

    int countByBakeryId(Long bakeryId);

    List<BakeryReview> findByBakeryId(Long bakeryId);
}