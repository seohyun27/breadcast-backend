package com.breadcrumbs.breadcast.domain.review.entity;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)  // 날짜를 자동으로 저장하는 Auditing 기능 활성화
public class MenuReview {

    @Id
    @GeneratedValue
    @Column(name = "menu_review_id")
    private long id;

    @Column(name = "menu_review_rating")
    private double rating;

    @Column(name = "menu_review_text")
    private String text;

    @CreatedDate   // 엔티티가 처음 저장될 때 자동으로 값을 삽입
    @Column(name = "menu_review_date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;


    /// 생성 메소드 ///
    public static MenuReview createMenuReview(double rating, String text, Member member, Bakery bakery, Menu menu) {
        MenuReview menuReview = new MenuReview();
        menuReview.rating = rating;
        menuReview.text = text;
        menuReview.member = member;
        menuReview.bakery = bakery;
        menuReview.menu = menu;
        return menuReview;
    }


    /// 업데이트 메소드 ///
    public void update(double rating, String text){
        this.rating = rating;
        this.text = text;
    }
}
