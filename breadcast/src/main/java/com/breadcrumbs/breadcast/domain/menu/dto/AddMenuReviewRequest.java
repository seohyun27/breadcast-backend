package com.breadcrumbs.breadcast.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddMenuReviewRequest {
    /**
     * 메뉴 리뷰 쓰기
     */
    private long bakeryId;	//빵집 id
    private double rating;	// 별점
    private String text;	//리뷰 내용
}
