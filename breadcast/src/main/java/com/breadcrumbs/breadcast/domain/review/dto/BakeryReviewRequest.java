package com.breadcrumbs.breadcast.domain.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BakeryReviewRequest {
    /**
     * 가게 리뷰 쓰기
     * 가게 리뷰 수정하기
     */
    private double rating; //리뷰 별점
    private String text; //리뷰 내용
    private String photo; //리뷰 사진
}
