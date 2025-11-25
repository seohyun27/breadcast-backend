package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.domain.bakery.dto.SearchBakeryResponse;
import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.bakery.service.BakeryService;
import com.breadcrumbs.breadcast.domain.favorite.entity.FavoriteBakery;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.review.entity.BakeryReview;
import com.breadcrumbs.breadcast.domain.review.repository.BakeryReviewRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BakeryServiceTest {

    @Autowired EntityManager em;
    @Autowired BakeryService bakeryService;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired BakeryReviewRepository bakeryReviewRepository;
    @Autowired FavoriteBakeryRepository favoriteBakeryRepository;
    @Autowired MemberRepository memberRepository;

    private Long memAId;
    private Long bakeryId;
    private Long bakeryAId;
    private Long bakeryBId;

    @BeforeEach
    void setup() {
        // 1. Bakery Entity 생성 및 저장
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

        // 2. Member Entity 생성 및 저장
        Member member1 = Member.createMember("user1", "pass", "유저1");
        Member member2 = Member.createMember("user2", "pass", "유저2");
        Member member3 = Member.createMember("user3", "pass", "유저3");
        Member member4 = Member.createMember("user4", "p", "유저4");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memAId = member1.getId();


        // 3. BakeryReview 저장 (평균: 4.0)
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

        // 4. FavoriteBakery 저장
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
    @DisplayName("가게 상세 정보 조회 시 모든 정보(리뷰, 스크랩, 평점)가 정확해야 한다")
    void getBakeryDetail_Success_With_Calculated_Data() {
        BakeryDetailResponse response = bakeryService.getBakeryDetail(bakeryId, memAId);

        assertNotNull(response);

        assertEquals(bakeryId.longValue(), response.getId());
        assertEquals(2, response.getFavoriteCount(), "스크랩 수가 일치해야 합니다.");
        assertEquals(3, response.getReviewCount(), "리뷰 수가 일치해야 합니다.");
        assertEquals(4.0, response.getRating(), 0.01, "평균 별점이 4.0으로 일치해야 합니다.");
        assertTrue(response.isFavorited(), "사용자가 좋아요를 표시했습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 가게 ID로 조회 시 예외가 발생해야 한다")
    void getBakeryDetail_ThrowsException_IfNotFound() {
        Long nonExistentId = 9999L;

        assertThrows(IllegalArgumentException.class, () -> {
            bakeryService.getBakeryDetail(nonExistentId, 1L);
        }, "존재하지 않는 ID 조회 시 IllegalArgumentException이 발생해야 합니다.");
    }

    @Test
    @DisplayName("키워드 검색 시 해당 키워드를 포함하는 빵집만 정렬되어야 한다")
    void search_withKeyword() {
        // When
        List<SearchBakeryResponse> result = bakeryService.searchBakeries("빵", "");

        // Then
        assertNotNull(result);
        assertEquals(3, result.size(), "빵집 3개가 검색되어야 합니다.");

        // 1. 검색 결과 검증
        assertEquals(bakeryBId, result.get(0).getId(), "첫번째는 B 빵집이어야 합니다.");
        assertEquals(bakeryAId, result.get(1).getId(), "두번째는 A 빵집이어야 합니다.");

        // 2. 데이터 검증
        assertEquals(4, result.get(0).getFavoriteCount(), "B 빵집의 스크랩 수는 4여야 합니다.");
        assertEquals(3, result.get(1).getFavoriteCount(), "A 빵집의 스크랩 수는 3여야 합니다.");
    }

    @Test
    @DisplayName("키워드 검색 시 해당 키워드를 포함하는 빵집과 리뷰순으로 정렬되어야 한다")
    void search_withKeyword_andReview() {

        // When
        List<SearchBakeryResponse> result = bakeryService.searchBakeries("빵","review");

        // Then
        assertNotNull(result);
        assertEquals(3, result.size(), "빵집 3개가 검색되어야 합니다.");

        // 1. 검색 결과 검증
        assertEquals(bakeryAId, result.get(0).getId(), "첫번째는 A 빵집이어야 합니다.");
        assertEquals(bakeryId, result.get(1).getId(), "두번째는 테스트 빵집이어야 합니다.");

        // 2. 데이터 검증
        assertEquals(5, result.get(0).getReviewCount(), "A 빵집의 리뷰 수는 5여야 합니다.");
        assertEquals(3, result.get(1).getReviewCount(), "테스트 빵집의 리뷰 수는 3여야 합니다.");
    }

    @Test
    @DisplayName("검색 키워드 없이 전체 가게 조회 시 인기순(스크랩 수)으로 정렬되어야 한다")
    void getAllBakeries_SortedByPopularity() {
        // GIVEN:
        // 스크랩 수 순서: 빵B (4) > 빵A (3) > 테스트 빵집 (2)

        // WHEN: 키워드 없이, "POPULAR" 정렬 기준으로 서비스 호출 (또는 전용 메서드 호출)
        // NOTE: 기존 searchBakeries 메서드를 재활용하여 keyword="", sort="POPULAR"로 호출하거나,
        // 새로운 findAllPopularBakeries 메서드를 호출한다고 가정하고 테스트를 작성합니다.

        // 여기서는 기존 searchBakeries 메서드 로직을 확장하여 테스트합니다. (keyword="" + sort="")
        List<SearchBakeryResponse> result = bakeryService.searchBakeries("", "");

        // THEN:
        assertNotNull(result);
        assertEquals(3, result.size(), "DB에 저장된 빵집 3개가 모두 조회되어야 합니다.");

        // 1. 정렬 순서 검증 (스크랩 수: 4 > 3 > 2)
        // 1위: 빵B (스크랩 4개)
        assertEquals(bakeryBId, result.get(0).getId(), "첫 번째는 스크랩 수가 가장 많은 B 빵집이어야 합니다.");
        assertEquals(4, result.get(0).getFavoriteCount(), "B 빵집의 스크랩 수는 4여야 합니다.");

        // 2위: 빵A (스크랩 3개)
        assertEquals(bakeryAId, result.get(1).getId(), "두 번째는 스크랩 수 3개인 A 빵집이어야 합니다.");
        assertEquals(3, result.get(1).getFavoriteCount(), "A 빵집의 스크랩 수는 3여야 합니다.");

        // 3위: 테스트 빵집 (스크랩 2개)
        assertEquals(bakeryId, result.get(2).getId(), "세 번째는 스크랩 수 2개인 테스트 빵집이어야 합니다.");
        assertEquals(2, result.get(2).getFavoriteCount(), "테스트 빵집의 스크랩 수는 2여야 합니다.");
    }
}