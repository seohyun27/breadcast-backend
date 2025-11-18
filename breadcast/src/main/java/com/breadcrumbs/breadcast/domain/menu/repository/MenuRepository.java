package com.breadcrumbs.breadcast.domain.menu.repository;

import com.breadcrumbs.breadcast.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 bakeryId와 일치하는 모든 메뉴 엔티티를 Menu 데이터베이스에서 찾아 리스트로 반환
    List<Menu> findByBakeryId(Long bakeryId);

}
