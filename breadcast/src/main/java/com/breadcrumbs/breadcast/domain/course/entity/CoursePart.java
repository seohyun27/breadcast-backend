package com.breadcrumbs.breadcast.domain.course.entity;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
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

    private String body;

    @Column(name = "course_part_photo")
    private String photo;

    private double distance;
    private long travelMinute;

    // Course가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    // Bakery가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;


    /// 생성 메소드 ///
    public static CoursePart createCoursePart(long travelOrder, String body, String photo,
                      double distance, long travelMinute, Course course, Bakery bakery) {
        CoursePart coursePart = new CoursePart();
        coursePart.travelOrder = travelOrder;
        coursePart.body = body;
        coursePart.photo = photo;
        coursePart.distance = distance;
        coursePart.travelMinute = travelMinute;
        coursePart.course = course;
        coursePart.bakery = bakery;
        return coursePart;
    }
}

