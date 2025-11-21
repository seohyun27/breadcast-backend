package com.breadcrumbs.breadcast.domain.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMenusResponse {
    /**
     * 가게 메뉴 목록 보기
     */
    private long id; // 메뉴 ID
    private String name; // 메뉴 이름
    private double rating; // 메뉴 평균 별점
    private long count; // 메뉴 리뷰 수
}
