package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoursePartRequest {
    /**
     * 코트 파트 DTO
     * 코스 DTO 내부에서 사용한다
     */
    private long bakery_id;		// 해당 코스 파트에 해당하는 빵집의 id
    private String text;			// 파트 내용
    private String photo;		// 첨부 사진
    private double distance; 	// 프론트에서 받지 않는다면 지울 예정
    private long travelMinute;	// 프론트에서 받지 않는다면 지울 예정
}
