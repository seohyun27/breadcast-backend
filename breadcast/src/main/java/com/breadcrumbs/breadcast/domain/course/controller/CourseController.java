package com.breadcrumbs.breadcast.domain.course.controller;

import com.breadcrumbs.breadcast.domain.course.dto.*;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.course.service.CourseService;
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

    private final CourseService courseService;

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
