package com.breadcrumbs.breadcast.dto.bakery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchBakeryRequest {
    /**
     * 가게 검색 및 정렬하기
     */
    private String text; //검색어
    private String sortBy;
}
