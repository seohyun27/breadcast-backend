package com.breadcrumbs.breadcast.domain.member.controller;

import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.MemberUpdateRequest;
import com.breadcrumbs.breadcast.domain.member.dto.GetMyBakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.member.dto.GetMyCourseResponse;
import com.breadcrumbs.breadcast.domain.member.dto.GetMyCourseReviewResponse;
import com.breadcrumbs.breadcast.domain.member.dto.GetMyMenuReviewResponse;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryReviewRepository;
import com.breadcrumbs.breadcast.domain.course.repository.CourseRepository;
import com.breadcrumbs.breadcast.domain.course.repository.CourseReviewRepository;
import com.breadcrumbs.breadcast.domain.menu.repository.MenuReviewRepository;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/me")
public class MemberController {

    private final MemberService memberService;
    private final BakeryReviewRepository bakeryReviewRepository;
    private final MenuReviewRepository menuReviewRepository;
    private final CourseRepository courseRepository;
    private final CourseReviewRepository courseReviewRepository;

    @DeleteMapping()
    public ResponseEntity<Void> deleteMember(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PatchMapping()
    public ResponseEntity<MemberResponse> updateNickname(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @RequestBody @Valid MemberUpdateRequest request){
        return null;
    }

    @GetMapping("/bakery-reviews")
    public List<GetMyBakeryReviewResponse> getMyBakeryReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/menu-reviews")
    public List<GetMyMenuReviewResponse> getMyMenuReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses")
    public List<GetMyCourseResponse> getMyCourse(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/course-reviews")
    public List<GetMyCourseReviewResponse> getMyCourseReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
