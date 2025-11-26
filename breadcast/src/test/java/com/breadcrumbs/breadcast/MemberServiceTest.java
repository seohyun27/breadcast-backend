package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.member.dto.MemberResponse;
import com.breadcrumbs.breadcast.domain.member.dto.MemberUpdateRequest;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.member.service.MemberService;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MemberServiceTest {

    @Autowired EntityManager em;
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    private Long memberId;
    private final String OLD_NICKNAME = "원래닉네임";
    private final String NEW_NICKNAME = "새로운닉네임";
    private final String EXISTING_NICKNAME = "이미있는닉네임";

    @BeforeEach
    void setup() {
        // 테스트용 멤버 생성 및 저장
        Member member = Member.createMember("testUser", "password", OLD_NICKNAME);
        memberRepository.save(member);
        memberId = member.getId();

        // 닉네임 중복 테스트를 위한 다른 멤버 생성 및 저장
        Member otherMember = Member.createMember("otherUser", "password", EXISTING_NICKNAME);
        memberRepository.save(otherMember);

        em.flush();
        em.clear();
    }

    // --- 1. 닉네임 변경 성공 테스트 ---

    @Test
    @DisplayName("닉네임 변경 성공: 유효한 ID와 중복되지 않는 새 닉네임으로 업데이트")
    void updateNickname_Success() {
        // Given
        MemberUpdateRequest request = new MemberUpdateRequest();
        request.setNickname(NEW_NICKNAME);

        // When
        MemberResponse response = memberService.updateNickname(memberId, request);

        // Then
        // 1. 서비스 응답 DTO 검증
        assertNotNull(response);
        assertEquals(NEW_NICKNAME, response.getNickname(), "반환된 DTO의 닉네임이 새 닉네임이어야 합니다.");

        // 2. DB에 실제로 변경되었는지 검증 (영속성 컨텍스트 초기화 후 조회)
        em.flush();  // 변경사항을 DB에 반영
        em.clear();
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new AssertionError("멤버를 찾을 수 없습니다."));

        assertEquals(NEW_NICKNAME, foundMember.getNickname(), "DB에 저장된 닉네임도 새 닉네임이어야 합니다.");
    }

    // --- 2. 닉네임 중복 실패 테스트 ---

    @Test
    @DisplayName("닉네임 변경 실패: 사용하려는 닉네임이 이미 존재함")
    void updateNickname_Fail_NicknameExists() {
        // Given
        // 이미 다른 멤버가 사용 중인 닉네임으로 변경 요청
        MemberUpdateRequest request = new MemberUpdateRequest();
        request.setNickname(EXISTING_NICKNAME);

        // When & Then
        // GeneralException이 발생하는지 검증
        assertThrows(GeneralException.class, () -> {
            memberService.updateNickname(memberId, request);
        }, "이미 존재하는 닉네임으로 변경 시도 시 예외가 발생해야 합니다.");

        // 추가 검증: 닉네임이 변경되지 않고 유지되었는지 확인
        em.flush();  // 변경사항을 DB에 반영
        em.clear();
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new AssertionError("멤버를 찾을 수 없습니다."));
        assertEquals(OLD_NICKNAME, foundMember.getNickname(), "예외 발생 시 닉네임은 변경되지 않고 유지되어야 합니다.");
    }

    // --- 3. 멤버를 찾을 수 없을 때 실패 테스트 ---

    @Test
    @DisplayName("닉네임 변경 실패: 존재하지 않는 ID로 요청")
    void updateNickname_Fail_MemberNotFound() {
        // Given
        Long nonExistentId = 999L;
        MemberUpdateRequest request = new MemberUpdateRequest();
        request.setNickname(NEW_NICKNAME);

        // When & Then
        // 멤버를 찾을 수 없다는 예외가 발생하는지 검증 (MemberService의 orElseThrow 로직)
        assertThrows(GeneralException.class, () -> {
            memberService.updateNickname(nonExistentId, request);
        }, "존재하지 않는 ID로 요청 시 예외가 발생해야 합니다.");
    }
}