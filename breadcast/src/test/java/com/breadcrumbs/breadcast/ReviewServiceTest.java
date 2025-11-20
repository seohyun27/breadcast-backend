package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired EntityManager em;
    @Autowired ReviewService reviewService;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired BakeryReviewRepository bakeryReviewRepository;
    @Autowired MemberRepository memberRepository;

    private Long bakeryId;
    private Long currentMemId; // 현재 로그인한 사용자 ID

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

        // 3. BakeryReview 3개 저장 (평균: 4.0)
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5.0, "굿", null, member1, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4.0, "최고", null, member2, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3.0, "쏘쏘", null, member1, bakery));

        em.flush();
        em.clear();
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
