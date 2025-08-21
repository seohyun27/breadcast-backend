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
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "favoriteCourse_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String subTitle;

    private double allDistance;

    private long allTravelMinute;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursePart> courseParts = new ArrayList<>();

    /// 생성 메소드 ///
    public static Course createBakery(Member member, String title, String subTitle,
                                      double allDistance, long allTravelMinute,
                                      List<CoursePart> courseParts) {
        Course course = new Course();
        course.member = member;
        course.title = title;
        course.subTitle = subTitle;
        course.allDistance = allDistance;
        course.allTravelMinute = allTravelMinute;
        for (CoursePart coursePart : courseParts) {
            course.addCoursePart(coursePart); // 연관관계 편의 메소드 사용
        }
        return course;
    }

    /// 연관관계 편의 메소드///
    public void addCoursePart(CoursePart coursePart) {
        courseParts.add(coursePart);
        coursePart.setCourse(this);
    }
}
