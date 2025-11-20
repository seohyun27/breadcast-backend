package com.breadcrumbs.breadcast.domain.bakery.dto.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddReportRequest {
    /**
     * 제보글 쓰기
     */
    private String text;	// 제보글 내용
}
