package com.breadcrumbs.breadcast.repository.menu;

import com.breadcrumbs.breadcast.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 해당 메뉴에 달린 리뷰가 몇 개인지를 반환하는 메소드
    int countById(Long menuId);
}
