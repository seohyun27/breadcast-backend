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

    // 만약 빵집의 삭제로 인해 CoursePart 하나가 삭제됐을 때 Course에서 CoursePart들을 불러오는 순서에 오류가 발생함
    // 이를 해결할 로직 or 방법이 필요함
    private long travel_order;

    private String boby;

    private String photo;

    private double Distance;

    private long TravelMinute;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;

    public void setBakery(Bakery bakery) {
        this.bakery = bakery;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
