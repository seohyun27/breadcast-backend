package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetSimpleCoursesResponse {
    /**
     * 빵지순례 목록 보기 (좋아요 순)
     * 빵지순례 검색하기
     */
    private long course_id; 		// 루트 ID
    private String title; 			// 루트 제목
    private String description; 		// 루트 글 간략한 설명
    private String name; 			// 작성자 이름
    private String photo; 			// 빵지 순례 사진
    private long count; 			// 루트 글 좋아요 수
    private boolean isMine; 		// 이 글이 내가 작성한 글인지 아닌지 -> 이거 목록에서는 수정 버튼을 못 누르는 거면 빼야 될 듯
}
