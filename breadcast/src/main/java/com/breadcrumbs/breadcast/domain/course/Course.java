package com.breadcrumbs.breadcast.domain.course;

import com.breadcrumbs.breadcast.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private long id;

    // Member가 삭제될 때 해당 Course도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(name = "FK_course_to_member",
                    foreignKeyDefinition = "FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE"))
    private Member member;

    private String title;
    private String subTitle;
    private double allDistance;
    private long allTravelMinute;


    /// 생성 메소드 ///
    public static Course createCourse(Member member, String title, String subTitle,
                                      double allDistance, long allTravelMinute) {
        Course course = new Course();
        course.member = member;
        course.title = title;
        course.subTitle = subTitle;
        course.allDistance = allDistance;
        course.allTravelMinute = allTravelMinute;
        return course;
    }

    /// 업데이트 메소드 ///
    public void update(String title, String subTitle, double allDistance, long allTravelMinute){
        this.title = title;
        this.subTitle = subTitle;
        this.allDistance = allDistance;
        this.allTravelMinute = allTravelMinute;
    }


}

