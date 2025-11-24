package com.breadcrumbs.breadcast.domain.favorite.controller;

import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteBakeriesResponse;
import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteCoursesResponse;
import com.breadcrumbs.breadcast.domain.favorite.service.FavoriteService;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/me/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/bakeries")
    public List<GetFavoriteBakeriesResponse> getFavoriteBakeries(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/bakeries/{bakeryId}")
    public void addFavoriteBakery(@PathVariable Long bakeryId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
    }

    @DeleteMapping("/bakeries/{bakeryId}")
    public void deleteFavoriteBakery(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
    }

    @GetMapping("/courses")
    public List<GetFavoriteCoursesResponse> getFavoriteCourses(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/courses/{courseId}")
    public void addFavoriteCourse(@PathVariable Long courseId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteFavoriteCourse(@PathVariable Long courseId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
    }

}
