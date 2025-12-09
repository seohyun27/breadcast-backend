package com.breadcrumbs.breadcast.domain.course.controller;

import com.breadcrumbs.breadcast.domain.course.dto.CourseDetailResponse;
import com.breadcrumbs.breadcast.domain.course.dto.CourseRequest;
import com.breadcrumbs.breadcast.domain.course.dto.CourseResponse;
import com.breadcrumbs.breadcast.domain.course.dto.GetSimpleCoursesResponse;
import com.breadcrumbs.breadcast.domain.course.service.CourseService;
import com.breadcrumbs.breadcast.domain.review.dto.course.CourseReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.course.CourseReviewResponse;
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
public class CourseController {

    private final ReviewService reviewService;
    private final CourseService courseService;

    @PostMapping("/courses/{courseId}/course-reviews")
    public ApiResponse<CourseReviewResponse> addCourseReview(@PathVariable Long courseId,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                             @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    @PatchMapping("/course-reviews/{courseReviewId}")
    public ApiResponse<CourseReviewResponse> updateCourseReview(@PathVariable Long courseReviewId,
                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                       @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    @DeleteMapping("/course-reviews/{courseReviewId}")
    public ApiResponse<Void> deleteCourseReview(@PathVariable Long courseReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/courses")
    public ApiResponse<CourseResponse> createCourse(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody @Valid CourseRequest request){
        return null;
    }

    @PatchMapping("/courses/{courseId}")
    public ApiResponse<CourseResponse> updateCourse(@PathVariable Long courseId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody @Valid CourseRequest request){
        return null;
    }

    @DeleteMapping("/courses/{courseId}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long courseId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses/popular")
    public ApiResponse<List<GetSimpleCoursesResponse>> getPopularCourses(){
        return null;
    }

    @GetMapping("/courses")
    public ApiResponse<List<GetSimpleCoursesResponse>> searchCourses(@RequestParam String keyword){
        return null;
    }

    @GetMapping("/courses/{courseId}")
    public ApiResponse<CourseDetailResponse> getCourseDetail(@PathVariable Long courseId,
                                                @AuthenticationPrincipal(required = false) UserDetailsImpl userDetails){
        return null;
    }
}
