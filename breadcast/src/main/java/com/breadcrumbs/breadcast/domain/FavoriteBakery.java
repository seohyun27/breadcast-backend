package com.breadcrumbs.breadcast.domain;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;
}
