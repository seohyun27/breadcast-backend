package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.menu.Menu;
import com.breadcrumbs.breadcast.domain.menu.MenuReview;
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

    private MenuRepository menuRepository;
    private MenuReviewRepository menuReviewRepository;

    public List<Menu> getMenus(Long bakeryId){
        /*
        List<Menu> munus = menuRepository.findById(bakeryId);
        //반복문을 돌며 각 메뉴에 대해 평균 별점과 리뷰 수를 계산
        getAverageRating(menuId);
        menuRepository.countById(menuId);
         */

        return null;
    }

    private double getAverageRating(Long menuId){
        // List<MenuReview> menuReviews = MenuReviewRepository.findById(menuId);

        return 0;
    }
}
