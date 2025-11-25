package com.breadcrumbs.breadcast.domain.favorite.controller;

import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteBakeriesResponse;
import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteCoursesResponse;
import com.breadcrumbs.breadcast.domain.favorite.service.FavoriteService;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/me/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/bakeries")
    public ApiResponse<List<GetFavoriteBakeriesResponse>> getFavoriteBakeries(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/bakeries/{bakeryId}")
    public ApiResponse<Void> addFavoriteBakery(@PathVariable Long bakeryId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @DeleteMapping("/bakeries/{bakeryId}")
    public ApiResponse<Void> deleteFavoriteBakery(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses")
    public ApiResponse<List<GetFavoriteCoursesResponse>> getFavoriteCourses(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/courses/{courseId}")
    public ApiResponse<Void> addFavoriteCourse(@PathVariable Long courseId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @DeleteMapping("/courses/{courseId}")
    public ApiResponse<Void> deleteFavoriteCourse(@PathVariable Long courseId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
