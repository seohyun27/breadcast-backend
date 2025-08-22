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

    // bakery가 삭제될 때 menu도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id",
            foreignKey = @ForeignKey(name = "FK_mune_to_bakery",
                    foreignKeyDefinition = "FOREIGN KEY (bakery_id) REFERENCES bakery(bakery_id) ON DELETE CASCADE"))
    private Bakery bakery;

    /// 생성 메소드 ///
    public static Menu createMenu(String name, int price, String inform, String photo) {
        Menu menu = new Menu();
        menu.name = name;
        return menu;
    }
}

