package com.breadcrumbs.breadcast.domain.bakery.controller;

import com.breadcrumbs.breadcast.domain.bakery.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.domain.bakery.dto.SearchBakeryResponse;
import com.breadcrumbs.breadcast.domain.bakery.service.BakeryService;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<BakeryDetailResponse> getBakeryDetail(@PathVariable Long bakeryId,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 유저가 없으면 서비스로 null을 넘겨주도록 설계함
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return ApiResponse.onSuccess("빵집 정보 조회에 성공하였습니다.",
                bakeryService.getBakeryDetail(bakeryId, userId));
    }

    @GetMapping("/bakeries/{bakeryId}/bakery-reviews")
    public ApiResponse<List<BakeryReviewResponse>> getBakeryReviews(@PathVariable Long bakeryId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return ApiResponse.onSuccess("빵집 리뷰 조회에 성공하였습니다.",
                reviewService.getBakeryReviews(bakeryId, userId));
    }

    @PostMapping("/bakeries/{bakeryId}/bakery-reviews")
    public ApiResponse<BakeryReviewResponse> addBakeryReview(@PathVariable Long bakeryId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid BakeryReviewRequest request) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return ApiResponse.onSuccess("빵집 리뷰 추가에 성공하였습니다.",
                reviewService.addBakeryReview(bakeryId, userId, request));
    }

    @PatchMapping("/bakery-reviews/{bakeryReviewId}")
    public ApiResponse<BakeryReviewResponse> updateBakeryReview(@PathVariable Long bakeryReviewId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestBody @Valid BakeryReviewRequest request) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return ApiResponse.onSuccess("빵집 리뷰 수정에 성공하였습니다.",
                reviewService.updateBakeryReview(bakeryReviewId, userId, request));
    }

    @DeleteMapping("/bakery-reviews/{bakeryReviewId}")
    public ApiResponse<Void> bakeryReviewDelete(@PathVariable Long bakeryReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        reviewService.deleteBakeryReview(bakeryReviewId, userId);
        return ApiResponse.onSuccess("빵집 리뷰 삭제에 성공하였습니다.", null);
    }

    @GetMapping("/bakeries")
    public ApiResponse<List<SearchBakeryResponse>> searchBakeries(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "popular") String sort
    ) {
        return ApiResponse.onSuccess("빵집 조회에 성공하였습니다.",
                bakeryService.searchBakeries(keyword, sort));
    }
}
