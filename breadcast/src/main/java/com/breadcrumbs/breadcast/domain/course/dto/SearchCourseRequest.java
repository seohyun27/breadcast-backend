package com.breadcrumbs.breadcast.domain.course.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCourseRequest {
    /**
     * 빵지순례 검색하기
     */
    private String title; // 찾으려는 검색어
}
