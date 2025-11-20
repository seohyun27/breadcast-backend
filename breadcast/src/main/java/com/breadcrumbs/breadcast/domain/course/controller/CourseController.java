package com.breadcrumbs.breadcast.domain.course.controller;

import com.breadcrumbs.breadcast.domain.course.dto.CourseDetailResponse;
import com.breadcrumbs.breadcast.domain.course.dto.CourseRequest;
import com.breadcrumbs.breadcast.domain.course.dto.CourseResponse;
import com.breadcrumbs.breadcast.domain.course.dto.GetSimpleCoursesResponse;
import com.breadcrumbs.breadcast.domain.course.service.CourseService;
import com.breadcrumbs.breadcast.domain.review.dto.course.CourseReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.course.CourseReviewResponse;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> createCourse(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody @Valid CourseRequest request){
        return null;
    }

    @PatchMapping("/courses/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody @Valid CourseRequest request){
        return null;
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses/popular")
    public List<GetSimpleCoursesResponse> getPopularCourses(){
        return null;
    }

    @GetMapping("/courses")
    public List<GetSimpleCoursesResponse> searchCourses(@RequestParam String keyword){
        return null;
    }

    @GetMapping("/courses/{courseId}")
    public CourseDetailResponse getCourseDetail(@PathVariable Long courseId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
