package com.breadcrumbs.breadcast.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private long id;

    private String name;

    private int price;

    private String inform;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    /// 생성 메소드 ///
    /// 빵집을 추가하는 과정은 Bakery.addMenu()에서 이루어짐
    public static Menu createMenu(String name, int price, String inform, String photo) {
        Menu menu = new Menu();
        menu.name = name;
        return menu;
    }

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }
}

