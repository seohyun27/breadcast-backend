package com.breadcrumbs.breadcast.domain.menu;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classfy {

    @Id
    @GeneratedValue
    @Column(name = "classfy_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bread_id")
    private Bread bread;


    /// 생성 메소드 ///
    public static Classfy createClassfy(Menu menu, Bread bread) {
        Classfy classfy = new Classfy();
        classfy.menu = menu;
        classfy.bread = bread;
        return classfy;
    }
}
