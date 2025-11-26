package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.bakery.service.BakeryService;
import com.breadcrumbs.breadcast.domain.favorite.entity.FavoriteBakery;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.domain.favorite.service.FavoriteService;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.review.entity.BakeryReview;
import com.breadcrumbs.breadcast.domain.review.repository.BakeryReviewRepository;
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
public class FavoriteServiceTest {
    
    @Autowired EntityManager em;
    @Autowired BakeryService bakeryService;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired BakeryReviewRepository bakeryReviewRepository;
    @Autowired FavoriteBakeryRepository favoriteBakeryRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired FavoriteService favoriteService;

    private Long memAId;
    private Long memDId;
    private Long bakeryId;
    private Long bakeryAId;
    private Long bakeryBId;
    
    @BeforeEach
    void setup() {
        // Member Entity 생성 및 저장
        Member member1 = Member.createMember("user1", "pass", "유저1");
        Member member2 = Member.createMember("user2", "pass", "유저2");
        Member member3 = Member.createMember("user3", "pass", "유저3");
        Member member4 = Member.createMember("user4", "p", "유저4");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memAId = member1.getId();
        memDId = member4.getId();

        // Bakery Entity 생성 및 저장
        Bakery bakery = Bakery.createBakery(
                "테스트 빵집", "테스트 주소", "010-0000-0000", 37.0, 127.0,
                "http://test.com", "p1", "p2", "세상에서 제일 맛있는 빵집입니다.", "11:00 - 21:00");
        Bakery bakeryA = Bakery.createBakery(
                "맛있는 빵 A", "강남대로", "010-1111-1111", 37.5, 127.1,
                "pA", "pA1", "pA2", "놀러오세요", "10:00 - 12:00");;
        Bakery bakeryB = Bakery.createBakery(
                "최고의 빵 B", "판교대로", "010-2222-2222", 37.4, 127.2,
                "pB", "pB1", "pB2", "인기가 많습니다", "12:00 - 16:00");
        bakeryRepository.save(bakery);
        bakeryRepository.save(bakeryA);
        bakeryRepository.save(bakeryB);
        bakeryId = bakery.getId();
        bakeryAId = bakeryA.getId();
        bakeryBId = bakeryB.getId();

        // BakeryReview 저장 (평균: 4.0)
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "굿", null, member1, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "최고", null, member2, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3, "쏘쏘", null, member1, bakery));

        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "굿", null, member1, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "최고", null, member2, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3, "쏘쏘", null, member3, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "다섯", null, member4, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "다섯", null, member2, bakeryA)); // 총 5개

        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "맛있음", null, member1, bakeryB));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "천국", null, member2, bakeryB)); // 총 2개

        // FavoriteBakery 저장
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member1, bakery));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member2, bakery));

        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member1, bakeryA));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member2, bakeryA));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member3, bakeryA));

        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member1, bakeryB));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member2, bakeryB));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member3, bakeryB));
        favoriteBakeryRepository.save(FavoriteBakery.createFavoriteBakery(member4, bakeryB)); // 총 4개
        
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("관심 가게 추가 성공 시, DB에 저장되고 스크랩 카운트가 증가해야 한다")
    void addFavoriteBakery_Success() {
        // GIVEN: 빵A (bakeryAId)는 현재 스크랩 3개. User4 (memDId)는 아직 빵A를 스크랩하지 않았다.
        long initialCount = favoriteBakeryRepository.countByBakeryId(bakeryAId);

        // WHEN: User4가 빵A를 관심 가게로 추가
        favoriteService.addFavoriteBakery(bakeryAId, memDId);

        // THEN:
        // 1. DB 카운트 증가 확인
        assertEquals(initialCount + 1, favoriteBakeryRepository.countByBakeryId(bakeryAId), "전체 관심 가게 수가 1 증가해야 합니다.");

        // 2. 해당 멤버-빵집 관계가 DB에 존재하는지 확인
        assertTrue(favoriteBakeryRepository.existsByMemberIdAndBakeryId(memDId, bakeryAId), "해당 멤버와 빵집의 관심 가게 관계가 DB에 저장되어야 합니다.");
    }

    @Test
    @DisplayName("관심 가게 추가 실패: 이미 등록된 빵집을 다시 등록 시도")
    void addFavoriteBakery_Failure_AlreadyExists() {
        // GIVEN: User1 (memAId)은 이미 테스트 빵집 (bakeryId)을 관심 가게로 등록했다.

        // WHEN/THEN: 중복 등록 시도 시 예외 발생
        assertThrows(GeneralException.class, () -> {
            favoriteService.addFavoriteBakery(bakeryId, memAId); // User1이 테스트 빵집에 중복 등록 시도
        }, "이미 관심 가게로 등록된 빵집을 재등록 시도 시 GeneralException이 발생해야 합니다.");

        try {
            favoriteService.addFavoriteBakery(bakeryId, memAId);
        } catch (GeneralException e) {
            assertEquals("이미 관심 가게로 등록된 빵집입니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("관심 가게 추가 실패: 로그인하지 않은 사용자 (memId==null)")
    void addFavoriteBakery_Failure_NullMemberId() {
        // WHEN/THEN: memId가 null일 경우 예외 발생
        assertThrows(GeneralException.class, () -> {
            favoriteService.addFavoriteBakery(bakeryAId, null);
        }, "memId가 null일 경우 GeneralException이 발생해야 합니다.");

        try {
            favoriteService.addFavoriteBakery(bakeryAId, null);
        } catch (GeneralException e) {
            assertEquals("로그인한 사용자만 관심 가게를 등록할 수 있습니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("관심 가게 추가 실패: 존재하지 않는 사용자 ID")
    void addFavoriteBakery_Failure_MemberNotFound() {
        Long nonExistentMemberId = 9999L;

        // WHEN/THEN: Member를 찾을 수 없을 경우 예외 발생
        assertThrows(GeneralException.class, () -> {
            favoriteService.addFavoriteBakery(bakeryAId, nonExistentMemberId);
        }, "존재하지 않는 사용자 ID로 시도 시 GeneralException이 발생해야 합니다.");

        try {
            favoriteService.addFavoriteBakery(bakeryAId, nonExistentMemberId);
        } catch (GeneralException e) {
            assertEquals("사용자 정보를 찾을 수 없습니다.", e.getMessage());
        }
    }

    @Test
    @DisplayName("관심 가게 추가 실패: 존재하지 않는 빵집 ID")
    void addFavoriteBakery_Failure_BakeryNotFound() {
        Long nonExistentBakeryId = 9999L;

        // WHEN/THEN: Bakery를 찾을 수 없을 경우 예외 발생
        assertThrows(GeneralException.class, () -> {
            favoriteService.addFavoriteBakery(nonExistentBakeryId, memAId);
        }, "존재하지 않는 빵집 ID로 시도 시 GeneralException이 발생해야 합니다.");

        try {
            favoriteService.addFavoriteBakery(nonExistentBakeryId, memAId);
        } catch (GeneralException e) {
            assertEquals("빵집 정보를 찾을 수 없습니다.", e.getMessage());
        }
    }
}
