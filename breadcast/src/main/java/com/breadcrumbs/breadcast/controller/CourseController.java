package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.course.*;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.CoursePartService;
import com.breadcrumbs.breadcast.service.CourseService;
import com.breadcrumbs.breadcast.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final ReviewService reviewService;
    private final CourseService courseService;

    public ResponseEntity<CourseReviewResponse> addCourseReview(@PathVariable Long courseId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    public ResponseEntity<CourseReviewResponse> updateCourseReview(@PathVariable Long courseReviewId,
                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                       @RequestBody @Valid CourseReviewRequest request){
        return null;
    }

    public ResponseEntity<Void> deleteCourseReview(@PathVariable Long courseReviewId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<CourseResponse> createCourse(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       @RequestBody @Valid CourseRequest request){
        return null;
    }

    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long courseId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                       CourseRequest request){
        return null;
    }

    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<GetSimpleCoursesResponse> getPopularCourses(){
        return null;
    }

    public List<GetSimpleCoursesResponse> searchCourses(@RequestBody @Valid SearchCourseRequest request){
        return null;
    }

    public CourseDetailResponse getCourseDetail(@PathVariable Long courseId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
