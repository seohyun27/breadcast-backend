package com.breadcrumbs.breadcast.dto.myPage;

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
public class GetMyBakeryReviewResponse {
    /**
     * 내가 작성한 가게 리뷰 보기
     */
    private long bakeryId; 		//빵집 id
    private String name; 		//빵집 이름
    private long reviewId;		//빵집 리뷰 id
    private String text; 		//빵집 리뷰 내용
    private double rating; 		//빵집 리뷰 별점
    private String photo; 		//빵집 리뷰 사진

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date; 	//빵집 리뷰 쓴 시간
}
