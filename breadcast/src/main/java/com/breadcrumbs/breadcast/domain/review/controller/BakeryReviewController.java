package com.breadcrumbs.breadcast.domain.review.controller;

import com.breadcrumbs.breadcast.domain.review.dto.BakeryReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.BakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BakeryReviewController {

    private final ReviewService reviewService;

    @GetMapping("/bakeries/{bakeryId}/bakery-reviews")
    public List<BakeryReviewResponse> getBakeryReviews(@PathVariable Long bakeryId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return reviewService.getBakeryReviews(bakeryId, userId);
    }

    @PostMapping("/bakeries/{bakeryId}/bakery-reviews")
    public ResponseEntity<BakeryReviewResponse> addBakeryReview(@PathVariable Long bakeryId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid BakeryReviewRequest request){
        return null;
    }

    @PatchMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<BakeryReviewResponse> updateBakeryReview(@PathVariable Long bakeryReviewId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestBody @Valid BakeryReviewRequest request){
        return null;
    }

    @DeleteMapping("/bakery-reviews/{bakeryReviewId}")
    public ResponseEntity<Void> bakeryReviewDelete(@PathVariable Long bakeryReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }
}

