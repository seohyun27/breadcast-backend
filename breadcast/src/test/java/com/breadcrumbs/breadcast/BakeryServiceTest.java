package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import com.breadcrumbs.breadcast.dto.bakery.BakeryDetailResponse;
import com.breadcrumbs.breadcast.repository.MemberRepository;
import com.breadcrumbs.breadcast.repository.bakery.BakeryRepository;
import com.breadcrumbs.breadcast.repository.bakery.BakeryReviewRepository;
import com.breadcrumbs.breadcast.repository.bakery.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.service.BakeryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BakeryServiceTest {

    @Autowired EntityManager em;
    @Autowired BakeryService bakeryService;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired BakeryReviewRepository bakeryReviewRepository;
    @Autowired FavoriteBakeryRepository favoriteBakeryRepository;
    @Autowired MemberRepository memberRepository;

    private Long bakeryId;

    @BeforeEach
    void setup() {
        // 1. Bakery Entity 생성 및 저장
        Bakery bakery = Bakery.createBakery(
                "테스트 빵집", "테스트 주소", "010-0000-0000", 37.0, 127.0,
                "http://test.com", "p1", "p2");
        bakeryRepository.save(bakery);
        bakeryId = bakery.getId();

        // 2. Member Entity 생성 및 저장
        Member member1 = Member.createMember("user1", "pass", "유저1");
        Member member2 = Member.createMember("user2", "pass", "유저2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 3. BakeryReview 3개 저장 (평균: 4.0)
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "굿", null, member1, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "최고", null, member2, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3, "쏘쏘", null, member1, bakery));

        // 4. FavoriteBakery 2개 저장
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member1, bakery));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member2, bakery));

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("가게 상세 정보 조회 시 모든 정보(리뷰, 스크랩, 평점)가 정확해야 한다")
    void getBakeryDetail_Success_With_Calculated_Data() {
        BakeryDetailResponse response = bakeryService.getBakeryDetail(bakeryId, 999L);

        assertNotNull(response);

        assertEquals(bakeryId.longValue(), response.getId());
        assertEquals(2, response.getFavorite_count(), "스크랩 수가 일치해야 합니다.");
        assertEquals(3, response.getReview_count(), "리뷰 수가 일치해야 합니다.");
        assertEquals(4.0, response.getRating(), 0.01, "평균 별점이 4.0으로 일치해야 합니다.");
    }

    @Test
    @DisplayName("존재하지 않는 가게 ID로 조회 시 예외가 발생해야 한다")
    void getBakeryDetail_ThrowsException_IfNotFound() {
        Long nonExistentId = 9999L;

        assertThrows(IllegalArgumentException.class, () -> {
            bakeryService.getBakeryDetail(nonExistentId, 1L);
        }, "존재하지 않는 ID 조회 시 IllegalArgumentException이 발생해야 합니다.");
    }
}