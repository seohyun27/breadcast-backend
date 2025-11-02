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
public class CourseResponse {
    /**
     * 빵지순례 글쓰기
     * 빵지순례 글 수정하기
     */
    private String writer;			// 해당 글의 작성자(=본인 닉네임
    private String title;			// 글 제목
    private String subTitle;			// 부제목
    private double allDistance;		// 총길이
    private long allTravelMinute;		// 총시간
    private List<CoursePartResponse> parts;	// 해당하는 코스 파트들
}
