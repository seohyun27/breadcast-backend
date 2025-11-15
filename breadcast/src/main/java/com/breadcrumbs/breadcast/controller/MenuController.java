package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.menu.*;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.MenuService;
import com.breadcrumbs.breadcast.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final ReviewService reviewService;

    //프론트에게 GetMenusResponse dto로 이루어진 List 전달
    @GetMapping("/api/bakeries/{bakeryId}/menus")
    public List<GetMenusResponse> getMenus(@PathVariable Long bakeryId){
        List<GetMenusResponse> menuList = menuService.getMenus(bakeryId);
        return menuList;
    }

    public GetMenuDetailResponse getMenuDetail(@PathVariable Long menuId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<MenuReviewResponse> addMenuReview(@PathVariable Long menuId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody @Valid AddMenuReviewRequest request){
        return null;
    }

    public ResponseEntity<MenuReviewResponse> updateMenuReview(@PathVariable Long menuReviewId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody @Valid UpdateMenuReviewRequest request){
        return null;
    }

    public ResponseEntity<Void> deleteMenuReview(@PathVariable Long menuReviewId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
