package com.breadcrumbs.breadcast.dto.course;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResponse {
    /**
     * 빵지순례 세부 글 보기
     */
    private String writer;			// 해당 글의 작성자(=본인 닉네임)
    private String title;			// 글 제목
    private String subTitle;			// 부제목
    private double allDistance;		// 총길이
    private long allTravelMinute;		// 총시간
    private List<CoursePartResponse> parts;	// 해당하는 코스 파트들
    private List<CourseReviewResponse> reviews;	// 루트에 쓰인 리뷰들
    private boolean isMine	;		// 이 루트가 나의 글인지
}
