package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.course.Course;
import com.breadcrumbs.breadcast.dto.CourseDetailResponse;
import com.breadcrumbs.breadcast.repository.course.CoursePartRepository;
import com.breadcrumbs.breadcast.repository.course.CourseRepository;
import com.breadcrumbs.breadcast.repository.course.FavoriteCourseRepository;
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
    private final FavoriteCourseRepository favoriteCourseRepository;
    private final CoursePartRepository coursePartRepository;


    public Course createCourse(Long memId, Course course) {
        /*
        - Course 엔티티를 받아 데이터베이스에 저장합니다.
        - Course 엔티티 내에 포함된 CoursePart 리스트를 순회하며 각 빵집 ID의 유효성을 BakeryRepository.findById를 통해 확인합니다. (CoursePartService에서
        담당합니다.)
        - 유효성 검사를 통과하면, courseRepository.save를 호출하여 코스 엔티티를 데이터베이스에 저장합니다.
        - 코스 저장이 성공적으로 완료되면, coursePartService.createCourseParts(course.getId(), course.getCourseParts()) 를 호출하여 코스 구성 요소를 저장하도록 위임합니다.
        - 저장 후 Course 엔티티를 반환합니다.
        */

        return null;
    }

    public Course updateCourse(Long courseId, Long memId, Course updatedCourse) {
        /*
        - courseId에 해당하는 코스를 updatedCourse 엔티티의 내용으로 수정하는 메소드입니다.
        - memId가 해당 코스의 작성자인지 확인합니다.
        - courseRepository.save를 호출하여 DB에 코스 기본 정보를 반영합니다.
        - coursePartService.updateCourseParts(courseId, updatedCourse.getCourseParts()) 를 호출하여 기존 코스 구성 요소를 삭제하고 새로운 구성 요소를 저장하는 작업을 위임합니다.
        - 수정된 엔티티를 반환합니다.
        */

        return null;
    }

    public void deleteCourse(Long courseId, Long memId) {
        /*
        - courseId에 해당하는 코스와 그와 관련된 모든 데이터를 삭제하는 메소드입니다.
        - 현재 사용자가 해당 코스의 작성자인지 검증합니다.
        - 권한이 없으면 삭제를 진행하지 않습니다.
        - ON DELETE CASCADE 옵션을 통한 자동 삭제.Course만을 삭제하면 그와 관련된 모든 데이터가 삭제된다.courseRepository.deleteById(courseId) 를 호출하여 코스를 삭제한다.
        - 성공적인 삭제 후 void를 반환합니다.
        */
    }

    @Transactional(readOnly = true)
    public List<Course> getPopularCourses(int page, int size) {
        /*
        설명:코스 목록을 조회하고, 각 코스의 좋아요 수를 계산하여 인기 순으로 정렬한 후 페이징 처리된 결과를 반환합니다.
        - courseRepository.findAll() 을 호출하여 모든 Course 엔티티를 가져옵니다.
        - 가져온 코스 리스트를 순회하며, 각 코스에 대해 favoriteCourseRepository.countByCourseId(course.getId()) 를 호출하여 좋아요 수를 계산합니다.
        - 계산된 좋아요 수를 기준으로 코스 리스트를 내림차순으로 정렬합니다.
        - 정렬된 리스트에서 page와 size를 사용하여 필요한 만큼의 데이터만 잘라내어 Course 엔티티 리스트로 반환합니다.
        */

        return null;
    }

    @Transactional(readOnly = true)
    public List<Course> searchCourses(String keyword, int page, int size) {
        /*
        - 사용자가 입력한 keyword를 기반으로 빵지순례 코스를 검색하고, 좋아요 수를 포함한 엔티티 리스트를 반환합니다.
        - courseRepository.findByTitleContainingOrKeywordContaining을 호출하여 제목이나 키워드에 검색어가 포함된 코스 목록을 가져옵니다.
        - page와 size를 사용하여 페이징 처리도 함께 진행합니다.
        - 검색된 코스 목록을 순회하며, 각 Course 엔티티에 대해 favoriteCourseRepository.countByCourseId(course.getId()) 를 호출하여 좋아요 수를 계산합니다.
        - 모든 정보가 포함된 Course 엔티티 리스트를 반환합니다.
        */

        return null;
    }

    @Transactional(readOnly = true)
    public CourseDetailResponse getCourseDetail(Long courseId, Long memId) {
        /*
        - courseId를 받아 코스 상세 정보를 조회하고, memId에 따라 리뷰 작성자 권한 정보를 추가하여 CourseDetailResponse로 반환합니다.
        - courseRepository.findById(courseId) 를 호출하여 코스 기본 정보를 가져옵니다.
        - favoriteCourseRepository.countByCourseId(courseId) 를 호출하여 좋아요 총 개수를 가져옵니다.
        - coursePartRepository.findByCourseId(courseId) 를 호출하여 코스에 포함된 빵집 목록 (경로) 을 가져옵니다.
        - courseReviewRepository.findByCourseId(courseId) 를 호출하여 해당 코스의 리뷰 목록을 가져옵니다.
        - 각 리뷰에 대해 로그인한 사용자 (memId) 가 작성자인지 확인합니다.
        - 이 정보를 DTO에 포함합니다. (프론트엔드에서 수정 / 삭제 버튼을 띄울 수 있는지 판단할 수 있습니다.)
        - 가져온 모든 데이터를 CourseDetailResponse 객체에 담아 반환합니다.
        */

        return null;
    }

    @Transactional(readOnly = true)
    public List<Course> getMyCourse(Long memId) {
        /*
        - courseRepository.findByMemberId(memId)를 호출해 코스 목록을 가져온다
        - 해당하는 모든 코스에 대해 코스 제목/첫 번째 코스 파트의 사진 1장을 DTO로 묶어 리턴한다
        */

        return null;
    }
}
