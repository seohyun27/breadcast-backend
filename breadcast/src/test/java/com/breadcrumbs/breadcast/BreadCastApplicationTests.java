package com.breadcrumbs.breadcast;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.domain.menu.entity.Menu;
import com.breadcrumbs.breadcast.domain.review.entity.MenuReview;
import com.breadcrumbs.breadcast.domain.review.repository.MenuReviewRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@Transactional
class BreadCastApplicationTests {

	@Autowired EntityManager em;
	@Autowired
    MemberRepository memberRepository;
	@Autowired
    MenuReviewRepository menuReviewRepository;

	@Test
	@Rollback(false)
	void DB테스트() {
		//given
		Member member = Member.createMember("yu1234", "yu1234", "yu");
		Bakery bakery = Bakery.createBakery("영남 빵집", "대구 광역시", "010-1234-1234",
				1.4, 1.5 , "hello/bakery", "photo1", "photo2");
		Menu menu = Menu.createMenu("소금빵", 3000, "바삭바삭 소금빵", "photo", bakery);
		MenuReview menuReview = MenuReview.createMenuReview(4.5, "맛있어요", member, bakery, menu);

		//when
		memberRepository.save(member);
		em.persist(bakery);
		em.persist(menu);
		menuReviewRepository.save(menuReview);

		//then
		List<MenuReview> reviews = menuReviewRepository.findByMemberId(member.getId());
		assertThat(reviews.get(0).getMember()).isEqualTo(member);

		// em.remove(bakery);
		// 원래라면 베이커리 삭제 시 메뉴도 함께 삭제되어야 함
		// 그런데 뭔가 결과가 이상해짐. 이 부분에 오류 있음
	}
}
