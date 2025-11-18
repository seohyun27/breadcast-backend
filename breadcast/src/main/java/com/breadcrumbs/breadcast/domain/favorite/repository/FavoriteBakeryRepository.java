package com.breadcrumbs.breadcast.domain.favorite.repository;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.bakery.entity.FavoriteBakery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteBakeryRepository extends JpaRepository<FavoriteBakery, Long> {

    List<FavoriteBakery> findByMemberId(Long memId);

    void deleteByMemberIdAndBakeryId(Long memId, Long bakeryId);

    int countByBakeryId(Long bakeryId);
}