package com.breadcrumbs.breadcast.domain.bakery.controller;

import com.breadcrumbs.breadcast.domain.bakery.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.domain.bakery.dto.SearchBakeryResponse;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.bakery.service.BakeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BakeryController {

    private final BakeryService bakeryService;

    @GetMapping("/bakeries/{bakeryId}")
    public BakeryDetailResponse getBakeryDetail(@PathVariable Long bakeryId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails){
        // 로그인 유저가 없으면 서비스로 null을 넘겨주도록 설계함
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        return bakeryService.getBakeryDetail(bakeryId, userId);
    }

    @GetMapping("/bakeries")
    public List<SearchBakeryResponse> searchBakeries(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "popular") String sort
    ) {
        return null;
    }
}
