package com.breadcrumbs.breadcast.domain.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFavoriteBakeriesResponse {
    /**
     * 관심 가게 목록 보기
     */
    private long id; 	 //빵집 id
    private String name;	 //빵집 이름
    private String address;	 //빵집 주소
    private String phone;	 //빵집 연락처
    private String photo1;	 //빵집 사진1
    private String photo2;	 //빵집 사진2
}
