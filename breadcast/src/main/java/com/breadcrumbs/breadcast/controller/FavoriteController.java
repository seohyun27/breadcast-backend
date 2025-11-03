package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.myPage.GetFavoriteBakeriesResponse;
import com.breadcrumbs.breadcast.dto.myPage.GetFavoriteCoursesResponse;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    public List<GetFavoriteBakeriesResponse> getFavoriteBakeries(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<Void> addFavoriteBakery(@PathVariable Long bakeryId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<Void> deleteFavoriteBakery(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public List<GetFavoriteCoursesResponse> getFavoriteCourses(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<Void> addFavoriteCourse(@PathVariable Long courseId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<Void> deleteFavoriteCourse(@PathVariable Long courseId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
