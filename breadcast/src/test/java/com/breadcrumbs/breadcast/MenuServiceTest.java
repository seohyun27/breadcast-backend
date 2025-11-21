package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.menu.dto.GetMenusResponse;
import com.breadcrumbs.breadcast.domain.menu.entity.Menu;
import com.breadcrumbs.breadcast.domain.menu.repository.MenuRepository;
import com.breadcrumbs.breadcast.domain.menu.service.MenuService;
import com.breadcrumbs.breadcast.domain.review.entity.MenuReview;
import com.breadcrumbs.breadcast.domain.review.repository.MenuReviewRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
// @Rollback(false) // 테스트 후 DB 롤백이 기본 동작이므로 주석 처리
public class MenuServiceTest {

    @Autowired EntityManager em;
    @Autowired MemberRepository memberRepository;
    @Autowired MenuRepository menuRepository;
    @Autowired MenuReviewRepository menuReviewRepository;
    @Autowired BakeryRepository bakeryRepository;
    @Autowired MenuService menuService;

    private long bakeryId;
    private long menuAId;
    private long menuBId;

    @BeforeEach
    void setup() {
        // 1. Member 생성 및 저장
        Member member1 = Member.createMember("user1", "pass", "유저1");
        Member member2 = Member.createMember("user2", "pass", "유저2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 2. Bakery 및 Menu 생성 및 저장
        Bakery bakery = Bakery.createBakery(
                "테스트 빵집", "테스트 주소", "010-0000-0000", 37.0, 127.0,
                "http://test.com", "p1", "p2");
        bakeryRepository.save(bakery);
        bakeryId = bakery.getId();

        Menu menuA = Menu.createMenu("앙버터", 4500, "맛있다", "test/folder", bakery);
        Menu menuB = Menu.createMenu("소금빵", 3900, "부드럽다", "test/folder", bakery);
        menuRepository.save(menuA);
        menuRepository.save(menuB);
        menuAId = menuA.getId();
        menuBId = menuB.getId();

        // 4. Menu A에 대한 리뷰 3개 저장 (평균: (5.0 + 3.0 + 4.0) / 3 = 4.0)
        MenuReview menuReview1 = MenuReview.createMenuReview(5.0, "부드러워요", member1, bakery, menuA);
        MenuReview menuReview2 = MenuReview.createMenuReview(3.0, "퍽퍽해요", member1, bakery, menuA);
        MenuReview menuReview3 = MenuReview.createMenuReview(4.0, "맛있어요", member2, bakery, menuA);
        menuReviewRepository.save(menuReview1);
        menuReviewRepository.save(menuReview2);
        menuReviewRepository.save(menuReview3);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("빵집 메뉴 목록 조회 시 모든 정보가 정확하게 DTO로 변환되어야 한다")
    void getMenus_Success_DataMappingVerification() {
        List<GetMenusResponse> responseList = menuService.getMenus(bakeryId);

        // 1. 리스트의 크기 확인
        assertNotNull(responseList);
        assertEquals(2, responseList.size(), "메뉴는 총 2개가 반환되어야 합니다.");

        // 2. 메뉴 A (앙버터)의 결과 확인 (리뷰 3개, 평점 4.0)
        GetMenusResponse menuA_res = responseList.stream()
                .filter(r -> r.getId() == menuAId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("앙버터 메뉴 DTO를 찾을 수 없습니다."));

        assertEquals(menuAId, menuA_res.getId(), "메뉴 A의 ID가 일치해야 합니다.");
        assertEquals("앙버터", menuA_res.getName(), "메뉴 A의 이름이 일치해야 합니다.");
        assertEquals(3, menuA_res.getCount(), "앙버터의 리뷰 수는 3개여야 합니다.");
        assertEquals(4.0, menuA_res.getRating(), 0.01, "앙버터의 평균 평점은 4.0이어야 합니다.");


        // 3. 메뉴 B (소금빵)의 결과 확인 (리뷰 0개, 평점 0.0)
        GetMenusResponse menuB_res = responseList.stream()
                .filter(r -> r.getId() == menuBId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("소금빵 메뉴 DTO를 찾을 수 없습니다."));

        assertEquals(menuBId, menuB_res.getId(), "메뉴 B의 ID가 일치해야 합니다.");
        assertEquals("소금빵", menuB_res.getName(), "메뉴 B의 이름이 일치해야 합니다.");
        assertEquals(0, menuB_res.getCount(), "소금빵의 리뷰 수는 0개여야 합니다.");
        assertEquals(0.0, menuB_res.getRating(), 0.01, "소금빵의 평균 평점은 0.0이어야 합니다.");
    }
}
