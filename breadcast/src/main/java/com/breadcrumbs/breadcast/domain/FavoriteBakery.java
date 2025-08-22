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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(name = "FK_favorite_bakery_to_member",
                    foreignKeyDefinition = "FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id",
            foreignKey = @ForeignKey(name = "FK_favorite_bakery_to_bakery",
                    foreignKeyDefinition = "FOREIGN KEY (bakery_id) REFERENCES bakery(bakery_id) ON DELETE CASCADE"))
    private Bakery bakery;
}
