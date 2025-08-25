package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteBakeryRepository extends JpaRepository<FavoriteBakery, Long> {

    // Member 기준으로 즐겨찾기 빵집 리스트 조회
    List<FavoriteBakery> findByMemberOrderByDateDesc(Member member);
}