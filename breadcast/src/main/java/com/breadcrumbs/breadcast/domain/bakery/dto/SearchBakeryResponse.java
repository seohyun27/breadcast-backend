package com.breadcrumbs.breadcast.domain.bakery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBakeryResponse {
    /**
     * 가게 검색 및 정렬하기
     */
    private long id; 		// 빵집 id
    private String name;		// 빵집 이름
    private String address;		// 빵집 주소
    private String photo1;		// 빵집 사진
    private double rating; 		// 빵집 평균 별점
    private int favoriteCount; 	//빵집 스크랩 수
    private int reviewCount; 	//빵집 리뷰 수
    private String URL; //빵집 url
}
