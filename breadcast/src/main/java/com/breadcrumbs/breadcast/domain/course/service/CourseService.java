package com.breadcrumbs.breadcast.domain.course.service;

import com.breadcrumbs.breadcast.domain.course.entity.Course;
import com.breadcrumbs.breadcast.domain.course.dto.*;
import com.breadcrumbs.breadcast.domain.course.repository.CoursePartRepository;
import com.breadcrumbs.breadcast.domain.course.repository.CourseRepository;
import com.breadcrumbs.breadcast.domain.course.repository.CourseReviewRepository;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    // 코스 생성의 비즈니스 로직을 총괄
    // CourseRepository를 통해 코스 자체를 저장하고 삭제하며, CoursePartService에게 코스에 포함된 빵집 정보를 저장하도록 위임
    // 동시에 코스에 종속된 모든 관련 데이터(CoursePart, CourseLike, CourseReview)도 함께 삭제

    private final CourseRepository courseRepository;
    private final CoursePartService coursePartService;
    private final CoursePartRepository coursePartRepository;    // 코스 삭제 메소드 내에서 코스 파트를 삭제하기 위해 사용함!!
    private final FavoriteCourseRepository favoriteCourseRepository;
    private final CourseReviewRepository courseReviewRepository;


    public CourseResponse createCourse(Long memId, CourseRequest request) {

        return null;
    }

    public CourseResponse updateCourse(Long courseId, Long memId,
                                       CourseRequest request) {
        return null;
    }

    public void deleteCourse(Long courseId, Long memId) {

    }

    @Transactional(readOnly = true)
    public List<GetSimpleCoursesResponse> getPopularCourses() {

        return null;
    }

    @Transactional(readOnly = true)
    public List<GetSimpleCoursesResponse> searchCourses(SearchCourseRequest request) {

        return null;
    }

    @Transactional(readOnly = true)
    public CourseDetailResponse getCourseDetail(Long courseId, Long memId) {

        return null;
    }

    @Transactional(readOnly = true)
    public List<Course> getMyCourse(Long memId) {

        return null;
    }
}
