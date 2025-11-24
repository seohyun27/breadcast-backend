package com.breadcrumbs.breadcast.domain.bakery.controller;

import com.breadcrumbs.breadcast.domain.bakery.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.domain.bakery.dto.SearchBakeryResponse;
import com.breadcrumbs.breadcast.domain.bakery.service.BakeryService;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        BakeryReviewResponse bakeryReviewResponse = reviewService.addBakeryReview(bakeryId, userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bakeryReviewResponse);
    }

    @PatchMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<BakeryReviewResponse> updateBakeryReview(@PathVariable Long bakeryReviewId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestBody @Valid BakeryReviewRequest request) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        BakeryReviewResponse bakeryReviewResponse = reviewService.updateBakeryReview(
                bakeryReviewId,
                userId,
                request
        );
        return ResponseEntity.ok(bakeryReviewResponse);
    }

    @DeleteMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<Void> bakeryReviewDelete(@PathVariable Long bakeryReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        reviewService.deleteBakeryReview(bakeryReviewId, userId);

        //204 No Content 응답 반환 (성공적으로 처리되었으나 돌려줄 내용이 없음)
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bakeries")
    public List<SearchBakeryResponse> searchBakeries(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "popular") String sort
    ) {
        return bakeryService.searchBakeries(keyword, sort);
    }
}
