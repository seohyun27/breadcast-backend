package com.breadcrumbs.breadcast.domain.bakery;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String phone;
    // point 타입 변수 location
    private String URL;
    private String photo1;
    private String photo2;

    /// 생성 메소드 ///
    public static Bakery createBakery(String name, String address, String phone,
                                      String URL, String photo1, String photo2) {
        Bakery bakery = new Bakery();
        bakery.name = name;
        bakery.address = address;
        bakery.phone = phone;
        // location 없음
        bakery.URL = URL;
        bakery.photo1 = photo1;
        bakery.photo2 = photo2;
        return bakery;
    }
}
