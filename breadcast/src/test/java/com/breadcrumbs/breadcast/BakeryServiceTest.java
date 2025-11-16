package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import com.breadcrumbs.breadcast.dto.bakery.BakeryDetailResponse;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryRequest;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryResponse;
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

import java.util.List;

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
    private Long bakeryAId;
    private Long bakeryBId;

    @BeforeEach
    void setup() {
        // 1. Bakery Entity 생성 및 저장
        Bakery bakery = Bakery.createBakery(
                "테스트 빵집", "테스트 주소", "010-0000-0000", 37.0, 127.0,
                "http://test.com", "p1", "p2");
        Bakery bakeryA = Bakery.createBakery(
                "맛있는 빵 A", "강남대로", "010-1111-1111", 37.5, 127.1,
                "pA", "pA1", "pA2");;
        Bakery bakeryB = Bakery.createBakery(
                "최고의 빵 B", "판교대로", "010-2222-2222", 37.4, 127.2,
                "pB", "pB1", "pB2");
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

        // 3. BakeryReview 저장 (평균: 4.0)
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "굿", null, member1, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "최고", null, member2, bakery));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3, "쏘쏘", null, member1, bakery));

        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "굿", null, member1, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(5, "최고", null, member2, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(3, "쏘쏘", null, member3, bakeryA));
        bakeryReviewRepository.save(BakeryReview.createBakeryReview(4, "다섯", null, member1, bakeryA));
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

    @Test
    @DisplayName("정렬 기준이 리뷰순(REVIEW)일 때 리뷰 수에 따라 내림차순 정렬되어야 한다")
    void searchBakeries_sortByReview() {
        // Given
        SearchBakeryRequest request = new SearchBakeryRequest();
        request.setText(null); // 전체 검색
        request.setSortBy("review"); // 리뷰 순 정렬 요청 (A: 5개, B: 2개)

        // When
        List<SearchBakeryResponse> result = bakeryService.searchBakeries(request);

        // Then
        assertNotNull(result);
        assertEquals(3, result.size(), "총 3개의 빵집이 검색되어야 합니다.");

        // 1. 정렬 순서 검증: A (리뷰 5개) -> B (리뷰 2개) 순
        assertEquals(bakeryAId, result.get(0).getId(), "리뷰가 많은 A 빵집이 1등이어야 합니다.");
        assertEquals(bakeryBId, result.get(2).getId(), "리뷰가 적은 B 빵집이 3등이어야 합니다.");

        // 2. 데이터 검증 (A 빵집)
        assertEquals(5, result.get(0).getReview_count());
        assertEquals(3, result.get(0).getFavorite_count());
    }

    @Test
    @DisplayName("정렬 기준이 인기순(FAVORITE)일 때 스크랩 수에 따라 내림차순 정렬되어야 한다")
    void searchBakeries_sortByFavorite() {
        // Given
        SearchBakeryRequest request = new SearchBakeryRequest();
        request.setText(null); // 전체 검색
        request.setSortBy("favorite"); // 인기순 정렬 요청 (A: 2개, B: 4개)

        // When
        List<SearchBakeryResponse> result = bakeryService.searchBakeries(request);

        // Then
        assertNotNull(result);
        assertEquals(3, result.size(), "총 3개의 빵집이 검색되어야 합니다.");

        // 1. 정렬 순서 검증: B (스크랩 4개) -> A (스크랩 2개) 순
        assertEquals(bakeryBId, result.get(0).getId(), "B 빵집이 1등이어야 합니다.");
        assertEquals(bakeryAId, result.get(1).getId(), "A 빵집이 2등이어야 합니다.");

        // 2. 데이터 검증 (B 빵집)
        assertEquals(4, result.get(0).getFavorite_count());
        assertEquals(2, result.get(0).getReview_count());
    }

    @Test
    @DisplayName("키워드 검색 시 해당 키워드를 포함하는 빵집만 정렬되어야 한다")
    void searchBakeries_withKeyword() {
        // Given
        SearchBakeryRequest request = new SearchBakeryRequest();
        request.setText("맛있는"); // A 빵집 이름 포함
        request.setSortBy("favorite"); // 정렬 기준은 FAVORITE로 설정

        // When
        List<SearchBakeryResponse> result = bakeryService.searchBakeries(request);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size(), "A 빵집 1개만 검색되어야 합니다.");

        // 1. 검색 결과 검증
        assertEquals(bakeryAId, result.get(0).getId(), "검색 결과는 A 빵집이어야 합니다.");

        // 2. 데이터 검증
        assertEquals(2, result.get(0).getFavorite_count(), "A 빵집의 스크랩 수는 2여야 합니다.");
    }
}