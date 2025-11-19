package com.breadcrumbs.breadcast.dto.myPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFavoriteCoursesResponse {
    /**
     * 관심루트 목록 보기
     */
    private long id; 			//빵지순례(course) id
    private String title; 		//빵지 순례 제목
    private String subTitle; 	//빵지 순례 부제목
    private double allDistance;	//빵지 순례 총 거리
    private long allTravelMinute; 	//빵지순례 걸리는 시간
    private String photo1; 		//빵지 순례 사진
}
