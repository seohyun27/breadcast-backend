package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void deleteUser(Long memId){
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
