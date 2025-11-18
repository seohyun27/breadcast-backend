package com.breadcrumbs.breadcast.domain.favorite.controller;

import com.breadcrumbs.breadcast.domain.member.dto.GetFavoriteBakeriesResponse;
import com.breadcrumbs.breadcast.domain.member.dto.GetFavoriteCoursesResponse;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.favorite.service.FavoriteService;
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
    public ResponseEntity<Void> addFavoriteBakery(@PathVariable Long bakeryId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @DeleteMapping("/bakeries/{bakeryId}")
    public ResponseEntity<Void> deleteFavoriteBakery(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @GetMapping("/courses")
    public List<GetFavoriteCoursesResponse> getFavoriteCourses(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/courses/{courseId}")
    public ResponseEntity<Void> addFavoriteCourse(@PathVariable Long courseId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> deleteFavoriteCourse(@PathVariable Long courseId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
