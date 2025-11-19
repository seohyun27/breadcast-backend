package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteBakeryRepository extends JpaRepository<FavoriteBakery, Long> {

    List<FavoriteBakery> findByMemberId(Long memId);

    void deleteByMemberIdAndBakeryId(Long memId, Long bakeryId);

    int countByBakeryId(Long bakeryId);
}