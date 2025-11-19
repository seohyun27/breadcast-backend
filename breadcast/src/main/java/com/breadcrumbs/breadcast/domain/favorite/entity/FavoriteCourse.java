package com.breadcrumbs.breadcast.domain.course;

import com.breadcrumbs.breadcast.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteCourse {

    @Id
    @GeneratedValue
    @Column(name = "favorite_course_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;


    /// 생성 메소드 ///
    public static FavoriteCourse createFavoriteCourse(Member member, Course course) {
        FavoriteCourse favoriteCourse = new FavoriteCourse();
        favoriteCourse.member = member;
        favoriteCourse.course = course;
        return favoriteCourse;
    }
}
