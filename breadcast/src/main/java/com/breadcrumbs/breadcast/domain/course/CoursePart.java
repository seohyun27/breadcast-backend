package com.breadcrumbs.breadcast.domain.course;

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
    // 만약 빵집의 삭제로 인해 CoursePart 하나가 삭제됐을 때 Course에서 CoursePart들을 불러오는 순서에 오류가 발생함
    // 이를 해결할 로직 or 방법이 필요함

    private String body;

    @Column(name = "course_part_photo")
    private String photo;

    private double distance;
    private long travelMinute;

    // Course가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(name = "FK_course_part_to_course",
                    foreignKeyDefinition = "FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE"))
    private Course course;

    // Bakery가 삭제될 때 해당 CoursePart도 함께 삭제되어야 함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id",
            foreignKey = @ForeignKey(name = "FK_course_part_to_bakery",
                    foreignKeyDefinition = "FOREIGN KEY (bakery_id) REFERENCES bakery(bakery_id) ON DELETE CASCADE"))
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

