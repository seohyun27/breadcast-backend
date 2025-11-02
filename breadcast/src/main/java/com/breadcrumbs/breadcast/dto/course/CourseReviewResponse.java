package com.breadcrumbs.breadcast.dto.course;

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
public class CourseReviewResponse {
    /**
     * 빵지순례 리뷰 쓰기
     * 빵지순례 리뷰 수정하기
     */
    private long reivew_id;		// 리뷰 아이디
    private String writer;		// 작성자
    private String text;		// 내용

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;	// 작성일자

    private boolean isMine;		// 해당 로그인 유저의 리뷰인지 아닌지
}
