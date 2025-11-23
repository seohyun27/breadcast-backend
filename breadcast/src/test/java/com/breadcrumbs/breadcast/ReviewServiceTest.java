package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewRequest;
import com.breadcrumbs.breadcast.domain.review.dto.bakery.BakeryReviewResponse;
import com.breadcrumbs.breadcast.domain.review.entity.BakeryReview;
import com.breadcrumbs.breadcast.domain.review.repository.BakeryReviewRepository;
import com.breadcrumbs.breadcast.domain.review.service.ReviewService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReviewServiceTest {

    @Autowired EntityManager em;
    @Autowired ReviewService reviewService;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired BakeryReviewRepository bakeryReviewRepository;
    @Autowired MemberRepository memberRepository;

    private Long bakeryId;
    private Long currentMemId; // 현재 로그인한 사용자 ID
    private Long otherMemId; // 유저2 ID
    private Long reviewId;

    @BeforeEach
    void setup() {
        // 1. Bakery Entity 생성 및 저장
        Bakery bakery = Bakery.createBakery(
                "테스트 빵집", "주소", "010-0000-0000", 37.0, 127.0,
                "url", "p1", "p2");
        bakeryRepository.save(bakery);
        bakeryId = bakery.getId();

        // 2. Member Entity 생성 및 저장
        Member member1 = Member.createMember("user1", "pass", "유저1");
        Member member2 = Member.createMember("user2", "pass", "유저2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        currentMemId = member1.getId();
        otherMemId = member2.getId();

        // 3. BakeryReview 3개 저장 (평균: 4.0)
        BakeryReview review1 = bakeryReviewRepository.save(BakeryReview.createBakeryReview(5.0, "굿", null, member1, bakery));
        BakeryReview review2 = bakeryReviewRepository.save(BakeryReview.createBakeryReview(4.0, "최고", null, member2, bakery));
        BakeryReview review3 = bakeryReviewRepository.save(BakeryReview.createBakeryReview(3.0, "쏘쏘", null, member1, bakery));
        reviewId = review1.getId();

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("리뷰 수정 성공 시, DB에 반영되고 수정된 DTO가 반환되어야 한다")
    void updateBakeryReview_Success() {
        // GIVEN: 새로운 수정 요청 데이터 (별점, 내용 변경)
        BakeryReviewRequest updateRequest = new BakeryReviewRequest();
        updateRequest.setRating(1.5);
        updateRequest.setText("수정된 내용입니다.");
        updateRequest.setPhoto("updated_photo_url");

        // WHEN: 권한이 있는 사용자(currentMemId)가 리뷰 수정 서비스 호출
        BakeryReviewResponse response = reviewService.updateBakeryReview(
                reviewId,
                currentMemId,
                updateRequest
        );

        // THEN:
        // 1. 응답 DTO 검증
        assertEquals(updateRequest.getRating(), response.getRating(), "별점이 수정된 값과 일치해야 합니다.");
        assertEquals(updateRequest.getText(), response.getText(), "내용이 수정된 값과 일치해야 합니다.");
        assertTrue(response.isMine(), "수정 권한이 있는 사용자이므로 isMine은 true여야 합니다.");

        // 2. DB 반영 검증 (더티 체킹 확인)
        em.flush();
        em.clear();
        Optional<BakeryReview> savedReview = bakeryReviewRepository.findById(reviewId);

        assertTrue(savedReview.isPresent(), "수정된 리뷰가 DB에 존재해야 합니다.");
        assertEquals(updateRequest.getRating(), savedReview.get().getRating(), 0.01, "DB에 수정된 별점이 반영되어야 합니다.");
        assertEquals(updateRequest.getText(), savedReview.get().getText(), "DB에 수정된 내용이 반영되어야 합니다.");
    }

    @Test
    @DisplayName("리뷰 수정 시 권한이 없는 사용자가 시도하면 예외가 발생해야 한다")
    void updateBakeryReview_Failure_NoAuthority() {
        // GIVEN: 수정 요청 데이터
        BakeryReviewRequest updateRequest = new BakeryReviewRequest();
        updateRequest.setRating(1.0);
        updateRequest.setText("권한 없음 테스트");
        updateRequest.setPhoto("url");

        // WHEN/THEN: 권한이 없는 사용자(otherMemId)가 수정 서비스 호출 시 IllegalStateException 발생
        // (ReviewService의 권한 체크 로직에 따라 IllegalStateException 발생을 가정)
        assertThrows(IllegalStateException.class, () -> {
            reviewService.updateBakeryReview(
                    reviewId,
                    otherMemId, // 💡 권한 없는 사용자 ID
                    updateRequest
            );
        }, "작성자가 아닌 사용자가 수정 시도 시 IllegalStateException이 발생해야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 ID로 수정 시도 시 예외가 발생해야 한다")
    void updateBakeryReview_Failure_NotFound() {
        // GIVEN: 존재하지 않는 ID와 요청 데이터
        Long nonExistentId = 9999L;
        BakeryReviewRequest updateRequest = new BakeryReviewRequest();
        updateRequest.setRating(1.0);
        updateRequest.setText("존재하지 않음 테스트");
        updateRequest.setPhoto("url");

        // WHEN/THEN: IllegalArgumentException 발생 (ReviewService의 .orElseThrow() 로직)
        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.updateBakeryReview(
                    nonExistentId,
                    currentMemId,
                    updateRequest
            );
        }, "존재하지 않는 리뷰 ID 수정 시 IllegalArgumentException이 발생해야 합니다.");
    }

    @Test
    @DisplayName("리뷰 목록 조회 시 isMine 플래그가 정확히 계산되고 모든 정보가 매핑되어야 한다")
    void getBakeryReviews_Success_WithIsMineFlag() {
        // WHEN: 현재 사용자 ID(currentMemId)를 넘겨 리뷰 목록 조회
        List<BakeryReviewResponse> responseList = reviewService.getBakeryReviews(bakeryId, currentMemId);

        // THEN:
        assertNotNull(responseList);
        assertEquals(3, responseList.size(), "리뷰는 총 3개여야 합니다.");

        // 1. 첫 번째 리뷰 (나의 리뷰) 검증
        BakeryReviewResponse myReviewResponse = responseList.get(0);
        assertEquals("유저1", myReviewResponse.getWriter(), "첫 번째 리뷰는 현재 사용자의 닉네임이어야 합니다.");
        assertEquals(5.0, myReviewResponse.getRating(), 0.01);
        assertTrue(myReviewResponse.isMine(), "현재 사용자 ID와 작성자 ID가 일치하므로 isMine은 true여야 합니다.");

        // 2. 두 번째 리뷰 (다른 사람 리뷰) 검증
        BakeryReviewResponse otherReviewResponse = responseList.get(1);
        assertEquals("유저2", otherReviewResponse.getWriter(), "두 번째 리뷰는 다른 사용자의 닉네임이어야 합니다.");
        assertEquals(4.0, otherReviewResponse.getRating(), 0.01);
        assertFalse(otherReviewResponse.isMine(), "현재 사용자 ID와 작성자 ID가 다르므로 isMine은 false여야 합니다.");
    }

    @Test
    @DisplayName("리뷰가 없는 가게 ID로 조회 시 빈 리스트를 반환해야 한다")
    void getBakeryReviews_ReturnsEmptyList_IfNoReviews() {
        // GIVEN: 리뷰가 없는 새로운 빵집 ID
        Bakery bakeryEmpty = Bakery.createBakery("빈 빵집", "주소", "0", 0, 0, "url", "p1", "p2");
        bakeryRepository.save(bakeryEmpty);
        Long emptyBakeryId = bakeryEmpty.getId();

        // WHEN
        List<BakeryReviewResponse> responseList = reviewService.getBakeryReviews(emptyBakeryId, currentMemId);

        // THEN
        assertNotNull(responseList);
        assertTrue(responseList.isEmpty(), "리뷰가 없으면 빈 리스트가 반환되어야 합니다.");
    }
}
