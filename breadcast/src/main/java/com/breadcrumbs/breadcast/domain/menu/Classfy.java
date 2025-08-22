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

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "bread_id")
    private Bread bread;
}
