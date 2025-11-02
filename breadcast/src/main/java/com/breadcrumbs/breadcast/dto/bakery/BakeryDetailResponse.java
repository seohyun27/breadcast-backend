package com.breadcrumbs.breadcast.dto.bakery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BakeryDetailResponse {
    /**
     * 가게 정보 보기
     **/
    private long id; 		// 빵집 id
    private String name;		// 빵집 이름
    private String address;		// 빵집 주소
    private String phone;		// 빵집 연락처
    private double latitude;  	// 빵집의 위도 (y좌표)
    private double longitude; 	// 빵집의 경도 (x좌표)
    private String URL;		// 빵집 사이트
    private String photo1; 		// 빵집 사진
    private String photo2;		// 빵집 사진
    private double rating;		// 빵집 평균 별점
    private int favorite_count; 	//빵집 스크랩 수
    private int review_count; 	//빵집 리뷰 수
}
