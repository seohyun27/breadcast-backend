package com.breadcrumbs.breadcast.dto.menu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateMenuReviewRequest {
    /**
     * 메뉴 리뷰 수정하기
     */
    private double rating;	// 별점
    private String text;	// 리뷰 내용
}
