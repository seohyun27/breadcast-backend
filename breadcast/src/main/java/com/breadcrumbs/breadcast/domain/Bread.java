package com.breadcrumbs.breadcast.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bread {

    @Id
    @GeneratedValue
    @Column(name = "bread_id")
    private long id;

    @Column(name = "bread_name")
    private String name;

    private String category;

    public static Bread createBread(String name, String category){
        Bread bread = new Bread();
        bread.name = name;
        bread.category = category;
        return bread;
    }
}
