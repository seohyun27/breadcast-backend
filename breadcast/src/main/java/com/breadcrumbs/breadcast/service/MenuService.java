package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.menu.Menu;
import com.breadcrumbs.breadcast.repository.menu.MenuRepository;
import com.breadcrumbs.breadcast.repository.menu.MenuReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuReviewRepository menuReviewRepository;

    public List<Menu> getMenus(Long bakeryId) {

            /*
            List<Menu> munus = menuRepository.findByBakeryId(bakeryId);
            //반복문을 돌며 각 메뉴에 대해 평균 별점과 리뷰 수를 계산
              getAverageRating(menuId); // 평균 별점을 계산하는 private 메소드
             menuRepository.findByBakeryId(menuId);
             */

        return null;
    }

    public Menu getMenuDetail(Long menuId) {

            /*
            - menuRepository.findById를 사용해서 메뉴 정보를 가져옵니다.
            - menuReviewRepository.findById를 사용해서 특정 메뉴의 리뷰 목록을 가져옵니다.
            - menuReviewRepository.countById를 사용해서 리뷰 수를 가져옵니다.
            - menuService.getAverageRating를 사용해서 평균 별점 수를 계산합니다.
            - Menu 엔티티에 계산된 평균 별점과 리뷰 수를 직접 설정합니다.
            - 모든 정보가 반영된 Menu 엔티티를 반환합니다.
            */

        return null;
    }

    private double getAverageRating(Long menuId) {
            /*
            - 서비스 레이어에서 직접 평균을 계산합니다.
            - MenuReviewRepository.findByMenuId를 호출하여 모든 리뷰 데이터를 가져옵니다.
            - 가져온 리뷰 데이터를 활용해 평균값을 계산하고 반환합니다.
            */

        return 0;
    }
}
