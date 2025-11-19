package com.breadcrumbs.breadcast.dto.course;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequest {
    /**
     * 빵지순례 글쓰기
     * 빵지순례 글 수정하기
     */
    private String title;			// 글 제목
    private String subTitle;			// 부제목
    private List<CoursePartRequest> parts;	// 해당하는 코스 파트들. 순서대로 저장되어 있어야 함
}
