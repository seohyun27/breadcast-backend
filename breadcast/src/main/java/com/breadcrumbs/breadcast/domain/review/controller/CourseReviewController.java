package com.breadcrumbs.breadcast.domain.review.controller;

import com.breadcrumbs.breadcast.domain.course.dto.CourseReviewRequest;
import com.breadcrumbs.breadcast.domain.course.dto.CourseReviewResponse;
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
public class CourseReviewController {

    private final ReviewService reviewService;

    @PostMapping("/courses/{courseId}/course-reviews")
    public ResponseEntity<CourseReviewResponse> addCourseReview(@PathVariable Long courseId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    @PatchMapping("/course-reviews/{courseReviewId}")
    public ResponseEntity<CourseReviewResponse> updateCourseReview(@PathVariable Long courseReviewId,
                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                       @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    @DeleteMapping("/course-reviews/{courseReviewId}")
    public ResponseEntity<Void> deleteCourseReview(@PathVariable Long courseReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }
}

