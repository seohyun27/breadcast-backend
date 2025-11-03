package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.member.MemberResponse;
import com.breadcrumbs.breadcast.dto.member.MemberUpdateRequest;
import com.breadcrumbs.breadcast.dto.myPage.GetMyBakeryReviewResponse;
import com.breadcrumbs.breadcast.dto.myPage.GetMyCourseResponse;
import com.breadcrumbs.breadcast.dto.myPage.GetMyCourseReviewResponse;
import com.breadcrumbs.breadcast.dto.myPage.GetMyMenuReviewResponse;
import com.breadcrumbs.breadcast.repository.bakery.BakeryReviewRepository;
import com.breadcrumbs.breadcast.repository.course.CourseRepository;
import com.breadcrumbs.breadcast.repository.course.CourseReviewRepository;
import com.breadcrumbs.breadcast.repository.menu.MenuReviewRepository;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BakeryReviewRepository bakeryReviewRepository;
    private final MenuReviewRepository menuReviewRepository;
    private final CourseRepository courseRepository;
    private final CourseReviewRepository courseReviewRepository;

    public ResponseEntity<Void> deleteMember(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<MemberResponse> updateNickname(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @RequestBody @Valid MemberUpdateRequest request){
        return null;
    }

    public List<GetMyBakeryReviewResponse> getMyBakeryReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<GetMyMenuReviewResponse> getMyMenuReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<GetMyCourseResponse> getMyCourse(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<GetMyCourseReviewResponse> getMyCourseReview(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
