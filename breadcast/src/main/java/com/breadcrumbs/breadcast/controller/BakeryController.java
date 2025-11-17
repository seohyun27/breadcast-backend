package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.bakery.*;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.BakeryService;
import com.breadcrumbs.breadcast.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BakeryController {

    private final BakeryService bakeryService;
    private final ReviewService reviewService;

    @GetMapping("/bakeries/{bakeryId}")
    public BakeryDetailResponse getBakeryDetail(@PathVariable Long bakeryId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 유저가 없으면 서비스로 null을 넘겨주도록 설계함
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return bakeryService.getBakeryDetail(bakeryId, userId);
    }

    @GetMapping("/bakeries/{bakeryId}/bakery-reviews")
    public List<BakeryReviewResponse> getBakeryReviews(@PathVariable Long bakeryId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return reviewService.getBakeryReviews(bakeryId, userId);
    }

    @PostMapping("/bakeries/{bakeryId}/bakery-reviews")
    public ResponseEntity<BakeryReviewResponse> addBakeryReview(@PathVariable Long bakeryId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid BakeryReviewRequest request) {
        return null;
    }

    @PatchMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<BakeryReviewResponse> updateBakeryReview(@PathVariable Long bakeryReviewId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestBody @Valid BakeryReviewRequest request) {
        return null;
    }

    @DeleteMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<Void> bakeryReviewDelete(@PathVariable Long bakeryReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    @GetMapping("/bakeries")
    public List<SearchBakeryResponse> searchBakeries(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "popular") String sort
    ) {
        return bakeryService.searchBakeries(keyword, sort);
    }
}
