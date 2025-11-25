package com.breadcrumbs.breadcast.domain.member.controller;

import com.breadcrumbs.breadcast.domain.course.dto.GetMyCourseResponse;
import com.breadcrumbs.breadcast.domain.course.repository.CourseRepository;
import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.MemberUpdateRequest;
import com.breadcrumbs.breadcast.domain.member.service.MemberService;
import com.breadcrumbs.breadcast.domain.review.dto.myPage.GetMyBakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.review.dto.myPage.GetMyCourseReviewResponse;
import com.breadcrumbs.breadcast.domain.review.dto.myPage.GetMyMenuReviewResponse;
import com.breadcrumbs.breadcast.domain.review.repository.BakeryReviewRepository;
import com.breadcrumbs.breadcast.domain.review.repository.CourseReviewRepository;
import com.breadcrumbs.breadcast.domain.review.repository.MenuReviewRepository;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public void deleteMember(@AuthenticationPrincipal UserDetailsImpl userDetails){
    }

    @PatchMapping()
    public ApiResponse<MemberResponse> updateNickname(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                      @RequestBody @Valid MemberUpdateRequest request){
        return ApiResponse.onSuccess("닉네임 변경에 성공하였습니다.",
                memberService.updateNickname(userDetails.getUserId(), request));
    }

    @GetMapping("/bakery-reviews")
    public ApiResponse<List<GetMyBakeryReviewResponse>> getMyBakeryReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/menu-reviews")
    public ApiResponse<List<GetMyMenuReviewResponse>> getMyMenuReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses")
    public ApiResponse<List<GetMyCourseResponse>> getMyCourse(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/course-reviews")
    public ApiResponse<List<GetMyCourseReviewResponse>> getMyCourseReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
