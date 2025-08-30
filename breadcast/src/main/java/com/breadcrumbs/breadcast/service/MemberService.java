package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Member addMember(String loginId, String password) {
        /*
        // 유효성 검사 - 필요하다면 유효성 검사를 private으로 분리
        // 아이디의 길이가 5~20 사이이면서 영문과 숫자로만 이루어져있는지
        // 닉네임의 길이가 2~20자 사이이면서 영문과 숫자, 한글로만 이루어져 있는지
        // 비밀번호의 길이가 8~20자 사이이면서 영문+숫자+특수문자가 모두 1개 이상 포함되어 있는지
        // 이메일 중복 검사
        // 닉네임 중복 검사
        // 비밀번호 재확인 일치 여부


        // 1. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 2. Member 엔티티 생성
        // 이때 비밀번호는 encodedPassword로 집어넣을 것
        Member member = Member.createMember();

        // 3. UserRepository에 저장
        memberRepository.save(member);
        */

        return null;
    }


    public void deleteMember(Long memId){
        /*
        Member member = memberRepository.findById(memId);
        memberRepository.delete(memId);
         */
    }

    public Member updateNickname(Long memId, String newNickname){
        /*
        Member member = memberRepository.findById(memId);
        if (memberRepository.existsNickname(newNickname))
            //예외 발생
        else
            Member.setNickname(newNickname);
        return member;
         */

        // 이후 컨트롤러에서 세션 처리 : session.setAttribute("member", member);

        return null;
    };

}
