package com.breadcrumbs.breadcast.dto.bakery;

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
public class BakeryReviewResponse {
    /**
     * 가게 리뷰 보기
     * 가게 리뷰 쓰기
     * 가게 리뷰 수정하기
     **/
    private String writer; 		// 리뷰 작성자
    private double rating; 		// 리뷰 별점
    private String text; 		// 리뷰 내용
    private String photo; 		//리뷰 사진

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date; 	// 리뷰 쓴 날짜

    private boolean isMine;		// 현재 로그인 중인 사용자가 쓴 글인지 아닌지
}
