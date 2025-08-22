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
    @JoinColumn(name = "member_id",
            foreignKey = @ForeignKey(name = "FK_favorite_course_to_member",
                    foreignKeyDefinition = "FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(name = "FK_favorite_course_to_course",
                    foreignKeyDefinition = "FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE"))
    private Course course;
}
