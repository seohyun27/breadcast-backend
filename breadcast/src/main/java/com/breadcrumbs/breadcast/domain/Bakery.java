package com.breadcrumbs.breadcast.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bakery {

    @Id
    @GeneratedValue
    @Column(name = "bakery_id")
    private long id;

    @Column(name = "bakery_name")
    private String name;

    private String address;

    // point 타입 변수 locate

    private String phone;

    private String URL;

    private String photo1;

    private String photo2;

    // 빵집과 메뉴는 부모 자식 간의 종속 관계
    // 부모가 자식을 알고 있어야 cascade를 이용해 자신이 삭제될 때 자식을 함께 삭제할 수 있음
    @OneToMany(mappedBy = "bakery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "bakery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePart> courseParts = new ArrayList<>();

    /// 생성 메소드 ///
    public static Bakery createBakery(String name, String address, String phone,
                                      String URL, String photo1, String photo2,
                                      List<Menu> menus, List<CoursePart> courseParts) {
        Bakery bakery = new Bakery();
        bakery.name = name;
        bakery.address = address;
        bakery.phone = phone;
        bakery.URL = URL;
        bakery.photo1 = photo1;
        bakery.photo2 = photo2;
        for (Menu menu : menus) {
            bakery.addMenu(menu); // 연관관계 편의 메소드 사용
        }
        for (CoursePart coursePart : courseParts) {
            bakery.addCoursePart(coursePart); // 연관관계 편의 메소드 사용
        }
        return bakery;
    }

    /// 연관관계 편의 메소드 ///
    public void addMenu(Menu menu) {
        menus.add(menu);
        menu.setBakery(this); // Menu 쪽 FK도 연결
    }

    public void addCoursePart(CoursePart coursePart) {
        courseParts.add(coursePart);
        coursePart.setBakery(this);
    }
}
