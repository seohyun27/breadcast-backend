package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePartResponse {
    /**
     * 코트 파트 DTO
     * 코스 DTO 내부에서 사용한다
     */
    private long bakeryName;	// 해당 코스 파트에 해당하는 빵집의 이름
    private String address;		// 빵집의 주소
    private double latitude;  	// 빵집의 위도 (y좌표)
    private double longitude; 	// 빵집의 경도 (x좌표)
    private String text;			// 파트 내용
    private String photo;		// 첨부 사진
    private double distance; 	// 거리
    private long travelMinute;	// 여행 시간
}
