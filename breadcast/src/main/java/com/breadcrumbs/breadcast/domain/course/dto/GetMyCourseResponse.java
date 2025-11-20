package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMyCourseResponse {
    /**
     * 내가 작성한 루트 보기
     */
    private long courseId;		//빵지순례 id
    private String title; 		//빵지 순례 제목
    private String photo; 		//빵지 순례 사진
}
