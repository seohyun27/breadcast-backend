package com.breadcrumbs.breadcast.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "아이디는 필수입니다")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "아이디는 영문과 숫자로 구성된 5자 이상 20자 이하여야 합니다")
    private String loginId; 		// 유저 로그인 id

    @NotBlank(message = "비밀번호는 필수입니다")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,20}$", 
             message = "비밀번호는 영문, 숫자, 특수문자를 각각 하나 이상 포함하여 8자 이상 20자 이하여야 합니다")
    private String password; 	// 패스워드

    @NotBlank(message = "닉네임은 필수입니다")
    private String nickname; 	// 유저 닉네임
}

