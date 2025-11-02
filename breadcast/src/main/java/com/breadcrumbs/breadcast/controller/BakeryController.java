package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.bakery.*;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.BakeryService;
import com.breadcrumbs.breadcast.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BakeryController {

    private final BakeryService bakeryService;
    private final ReviewService reviewService;

    public BakeryDetailResponse getBakeryDetail(@PathVariable Long bakeryId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<BakeryReviewResponse> getBakeryReviews(@PathVariable Long bakeryId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<BakeryReviewResponse> addBakeryReview(@PathVariable Long bakeryId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid BakeryReviewRequest request){
        return null;
    }

    public ResponseEntity<BakeryReviewResponse> updateBakeryReview(@PathVariable Long bakeryReviewId,
                                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                   @RequestBody @Valid BakeryReviewRequest request){
        return null;
    }

    public ResponseEntity<Void> bakeryReviewDelete(@PathVariable Long bakeryReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<SearchBakeryResponse> searchBakeries(@RequestBody @Valid SearchBakeryRequest request){
        return null;
    }

}
