package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseReviewRequest {
    /**
     * 빵지순례 리뷰 쓰기
     * 빵지순례 리뷰 수정하기
     */
    private String text;	// 리뷰 내용
}
