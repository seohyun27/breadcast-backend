package com.breadcrumbs.breadcast.domain.menu.controller;

import com.breadcrumbs.breadcast.domain.menu.dto.GetMenuDetailResponse;
import com.breadcrumbs.breadcast.domain.menu.dto.GetMenusResponse;
import com.breadcrumbs.breadcast.domain.member.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/bakeries/{bakeryId}/menus")
    public List<GetMenusResponse> getMenus(@PathVariable Long bakeryId){
        return menuService.getMenus(bakeryId);
    }

    @GetMapping("/menus/{menuId}")
    public GetMenuDetailResponse getMenuDetail(@PathVariable Long menuId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
