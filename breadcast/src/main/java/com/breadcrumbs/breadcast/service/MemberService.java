package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)     // 기본을 읽기 모드로 설정 (DB 조회 이외의 삽입/삭제 시 false 옵션 필수!)
@RequiredArgsConstructor            // final을 포함한 생성자 자동 생성
public class MemberService {

    private final MemberRepository memberRepository;

    //@Transactional  // 읽기 전용이 아닌 메소드에 반드시 작성할 것


}
