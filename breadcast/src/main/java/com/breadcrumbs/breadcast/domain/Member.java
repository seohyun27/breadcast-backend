package com.breadcrumbs.breadcast.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 인자가 없는 기본 생성자를 protected로 생성(외부에서 호출 불가)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String loginId;

    private String password;

    private String nickname;

    public static Member createUser(String loginId, String password, String nickname){
        Member member = new Member();
        member.loginId = loginId;
        member.password = password;
        member.nickname = nickname;
        return member;
    }
}
