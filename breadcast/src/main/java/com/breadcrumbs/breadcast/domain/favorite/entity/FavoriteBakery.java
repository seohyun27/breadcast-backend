package com.breadcrumbs.breadcast.domain.favorite.entity;

import com.breadcrumbs.breadcast.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteBakery {

    @Id
    @GeneratedValue
    @Column(name = "favorite_bakery_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;


    /// 생성 메소드 ///
    public static FavoriteBakery createFavoriteBakery(Member member, Bakery bakery) {
        FavoriteBakery favoriteBakery = new FavoriteBakery();
        favoriteBakery.member = member;
        favoriteBakery.bakery = bakery;
        return favoriteBakery;
    }
}
