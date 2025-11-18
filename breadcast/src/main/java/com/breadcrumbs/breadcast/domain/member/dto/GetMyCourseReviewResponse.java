package com.breadcrumbs.breadcast.domain.member.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMyCourseReviewResponse {
    /**
     * 내가 작성한 루트 리뷰 보기
     */
    private long course_id;		 	//빵지 순례(course) id
    private long review_id;		 	//빵지 순례 리뷰 id
    private String course_nickname; 	//빵지 순례 작성자 닉네임
    private String title; 			//빵지 순례 제목
    private String review_nickname; 	//빵지 순례 리뷰 작성자 (=본인) 닉네임
    private String text; 			//빵지 순례 리뷰 내용

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;		//빵지 순례 리뷰 쓴 시간
}
