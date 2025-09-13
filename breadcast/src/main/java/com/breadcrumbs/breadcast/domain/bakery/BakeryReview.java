package com.breadcrumbs.breadcast.domain.bakery;

import com.breadcrumbs.breadcast.domain.Member;
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
public class BakeryReview {

    @Id
    @GeneratedValue
    @Column(name = "bakery_review_id")
    private long id;

    @Column(name = "bakery_review_rating")
    private double rating;

    @Column(name = "bakery_review_text")
    private String text;

    @Column(name = "bakery_review_photo")
    private String photo;

    @CreatedDate   // 엔티티가 처음 저장될 때 자동으로 값을 삽입
    @Column(name = "bakery_review_date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(name = "FK_bakery_review_to_member",
                    foreignKeyDefinition = "FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id",
            foreignKey = @ForeignKey(name = "FK_bakery_review_to_bakery",
                    foreignKeyDefinition = "FOREIGN KEY (bakery_id) REFERENCES bakery(bakery_id) ON DELETE CASCADE"))
    private Bakery bakery;


    /// 생성 메소드 ///
    public static BakeryReview createBakeryReview(double rating, String text,
                                                  String photo, Member member, Bakery bakery) {
        BakeryReview bakeryReview = new BakeryReview();
        bakeryReview.rating = rating;
        bakeryReview.text = text;
        bakeryReview.photo = photo;
        bakeryReview.member = member;
        bakeryReview.bakery = bakery;
        return bakeryReview;
    }

    /// 업데이트 메소드 ///
    public void update(double rating, String text, String photo){
        this.rating = rating;
        this.text = text;
        this.photo = photo;
    }
}