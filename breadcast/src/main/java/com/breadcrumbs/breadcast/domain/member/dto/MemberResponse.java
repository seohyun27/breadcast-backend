package com.breadcrumbs.breadcast.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    /**
     * 회원가입하기
     * 로그인하기
     * 프로필 수정하기
     */
    private String nickname; 	// 유저 닉네임
}
