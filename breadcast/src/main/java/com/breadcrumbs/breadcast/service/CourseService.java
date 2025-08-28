package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.course.Course;
import com.breadcrumbs.breadcast.repository.course.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseService {
    // 코스 생성의 비즈니스 로직을 총괄
    // CourseRepository를 통해 코스 기본 정보를 저장하고, CoursePartService에게 코스에 포함된 빵집 정보를 저장하도록 위임

    private CourseRepository courseRepository;
    private CoursePartService coursePartService;


    public Course createCourse(Long userId, Course course) {
        /*
        -Course 엔티티를 받아 데이터베이스에 저장합니다.
        -Course 엔티티 내에 포함된 CoursePart 리스트를 순회하며 각 빵집 ID의 유효성을 BakeryRepository.findById를 통해 확인합니다. (CoursePartService에서
        담당합니다.)
        -유효성 검사를 통과하면, courseRepository.save를 호출하여 코스 엔티티를 데이터베이스에 저장합니다.
        -코스 저장이 성공적으로 완료되면, coursePartService.createCourseParts(course.getId(), course.getCourseParts()) 를 호출하여 코스 구성
        요소를 저장하도록 위임합니다.
        - 저장 후 Course 엔티티를 반환합니다.
        */

        return null;
    }


}
