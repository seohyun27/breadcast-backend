package com.breadcrumbs.breadcast.domain.menu.controller;

import com.breadcrumbs.breadcast.dto.menu.*;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.MenuService;
import com.breadcrumbs.breadcast.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;
    private final ReviewService reviewService;

    @GetMapping("/bakeries/{bakeryId}/menus")
    public List<GetMenusResponse> getMenus(@PathVariable Long bakeryId){
        return menuService.getMenus(bakeryId);
    }

    @GetMapping("/menus/{menuId}")
    public GetMenuDetailResponse getMenuDetail(@PathVariable Long menuId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/menus/{menuId}/menu-reviews")
    public ResponseEntity<MenuReviewResponse> addMenuReview(@PathVariable Long menuId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                     @RequestBody @Valid AddMenuReviewRequest request){
        return null;
    }

    @PatchMapping("/menu-reviews/{menuReviewId}")
    public ResponseEntity<MenuReviewResponse> updateMenuReview(@PathVariable Long menuReviewId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody @Valid UpdateMenuReviewRequest request){
        return null;
    }

    @DeleteMapping("/menu-reviews/{menuReviewId}")
    public ResponseEntity<Void> deleteMenuReview(@PathVariable Long menuReviewId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
