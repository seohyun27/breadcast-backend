package com.breadcrumbs.breadcast.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateRequest {
    /**
     * 프로필 수정하기 (닉네임 변경하기)
     */
    private String nickname; //유저 닉네임
}
