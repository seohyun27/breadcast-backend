package com.breadcrumbs.breadcast.domain.review.controller;

import com.breadcrumbs.breadcast.domain.menu.dto.AddMenuReviewRequest;
import com.breadcrumbs.breadcast.domain.menu.dto.MenuReviewResponse;
import com.breadcrumbs.breadcast.domain.menu.dto.UpdateMenuReviewRequest;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuReviewController {

    private final ReviewService reviewService;

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

