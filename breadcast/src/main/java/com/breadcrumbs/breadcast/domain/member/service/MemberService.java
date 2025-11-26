package com.breadcrumbs.breadcast.domain.member.service;

import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.MemberUpdateRequest;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void deleteMember(Long memId){
        /*
        Member member = memberRepository.findById(memId);
        memberRepository.delete(memId);
         */
    }

    public MemberResponse updateNickname(Long memId, MemberUpdateRequest request) {
        // 1. 닉네임 중복 검사
        if (memberRepository.existsByNickname(request.getNickname())){
            throw new GeneralException("이미 사용 중인 닉네임입니다.");
        }

        // 2. Member 객체 조회 및 유무 확인
        Member member = memberRepository.findById(memId)
                .orElseThrow(() -> new GeneralException("해당 ID의 멤버를 찾을 수 없습니다."));

        // 3. Member 엔티티의 update 메소드 호출
        member.update(request.getNickname());

        // 4. 응답 객체 생성
        MemberResponse memberResponse = MemberResponse.builder()
                .nickname(member.getNickname())
                .build();

        return memberResponse;
    }
}
