package com.breadcrumbs.breadcast.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    /**
     * 로그인하기
     */
    private String LoginId; 		// 유저 로그인 id
    private String password; 	// 패스워드
}
