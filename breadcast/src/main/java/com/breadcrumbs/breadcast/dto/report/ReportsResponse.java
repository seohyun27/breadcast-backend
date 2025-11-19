package com.breadcrumbs.breadcast.dto.report;

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
public class ReportsResponse {
    /**
     * 제보글 보기
     * 제보글 쓰기
     */
    private long bakeryReportId; 	// 제보 글 ID
    private String title; 		// 제보 글의 제목
    private String name; 		// 작성자 이름

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;	// 제보 글 작성 시간

    private boolean isMine;		// 이 글이 내가 작성한 글인지 아닌지
}
