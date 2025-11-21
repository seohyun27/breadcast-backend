package com.breadcrumbs.breadcast.domain.review.entity;

import com.breadcrumbs.breadcast.domain.course.entity.Course;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)  // 날짜를 자동으로 저장하는 Auditing 기능 활성화
public class CourseReview {

    @Id
    @GeneratedValue
    @Column(name = "course_review_id")
    private long id;

    @Column(name = "course_review_text")
    private String text;

    @CreatedDate   // 엔티티가 처음 저장될 때 자동으로 값을 삽입
    @Column(name = "course_review_date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;



    /// 생성 메소드 ///
    public static CourseReview createCourseReview(String text, Member member, Course course) {
        CourseReview courseReview = new CourseReview();
        courseReview.text = text;
        courseReview.member = member;
        courseReview.course = course;
        return courseReview;
    }


    /// 업데이트 메소드 ///
    public void update(String text) {
        this.text = text;
    }

}
