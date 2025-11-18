package com.breadcrumbs.breadcast.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
    /**
     * 회원가입하기
     */
    private String loginId; 		// 유저 로그인 id
    private String password; 	// 패스워드
    private String nickname; 	// 유저 닉네임
}

