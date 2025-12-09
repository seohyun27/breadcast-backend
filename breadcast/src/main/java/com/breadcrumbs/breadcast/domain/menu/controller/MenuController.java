package com.breadcrumbs.breadcast.domain.menu.controller;

import com.breadcrumbs.breadcast.domain.menu.dto.GetMenuDetailResponse;
import com.breadcrumbs.breadcast.domain.menu.dto.GetMenusResponse;
import com.breadcrumbs.breadcast.domain.menu.service.MenuService;
import com.breadcrumbs.breadcast.domain.review.dto.menu.AddMenuReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.menu.MenuReviewResponse;
import com.breadcrumbs.breadcast.domain.review.dto.menu.UpdateMenuReviewRequest;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<List<GetMenusResponse>> getMenus(@PathVariable Long bakeryId){
        return ApiResponse.onSuccess("메뉴 조회에 성공하였습니다.",
                menuService.getMenus(bakeryId));
    }

    @GetMapping("/menus/{menuId}")
    public ApiResponse<GetMenuDetailResponse> getMenuDetail(@PathVariable Long menuId,
                                               @AuthenticationPrincipal(required = false) UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/menus/{menuId}/menu-reviews")
    public ApiResponse<MenuReviewResponse> addMenuReview(@PathVariable Long menuId,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @RequestBody @Valid AddMenuReviewRequest request){
        return null;
    }

    @PatchMapping("/menu-reviews/{menuReviewId}")
    public ApiResponse<MenuReviewResponse> updateMenuReview(@PathVariable Long menuReviewId,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                        @RequestBody @Valid UpdateMenuReviewRequest request){
        return null;
    }

    @DeleteMapping("/menu-reviews/{menuReviewId}")
    public ApiResponse<Void> deleteMenuReview(@PathVariable Long menuReviewId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }
}
