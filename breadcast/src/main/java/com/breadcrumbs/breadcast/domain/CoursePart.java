package com.breadcrumbs.breadcast.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePart {

    @Id
    @GeneratedValue
    @Column(name = "course_part_id")
    private long id;

    private long travelOrder;
    // 만약 빵집의 삭제로 인해 CoursePart 하나가 삭제됐을 때 Course에서 CoursePart들을 불러오는 순서에 오류가 발생함
    // 이를 해결할 로직 or 방법이 필요함

    private String body;
    private String photo;
    private double distance;
    private long travelMinute;

    // Course가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Bakery가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;
}

