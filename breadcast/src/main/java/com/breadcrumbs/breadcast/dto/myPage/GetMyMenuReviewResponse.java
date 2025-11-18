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
public class GetMyMenuReviewResponse {
    /**
     * 내가 작성한 메뉴 리뷰 보기
     */
    private long bakeryId;		//빵집 id
    private long menuId; 		//메뉴 id
    private long reviewId; 		//메뉴 리뷰 id
    private String bakeryName; 	//빵집 이름
    private String meunName; 	//메뉴 이름
    private String text; 		//메뉴 리뷰 내용
    private double rating; 		//메뉴 리뷰 별점

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date; 	//메뉴 리뷰 쓴 시간
}
