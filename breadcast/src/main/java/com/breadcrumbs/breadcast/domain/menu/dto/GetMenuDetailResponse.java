package com.breadcrumbs.breadcast.domain.menu.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMenuDetailResponse {
    /**
     * 메뉴 정보 보기 (리뷰 리스트 포함)
     */
    private long id;			// 메뉴 ID
    private String name;		// 메뉴 이름
    private int price;		// 메뉴 가격
    private String photo;		// 메뉴 사진
    private String inform;		// 메뉴 설명
    private double rating;		// 평균 별점
    private int count;		// 리뷰 수
    private List<MenuReviewResponse> reviews;	// 모든 메뉴 리뷰 목록
}
