package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.course.CoursePart;
import com.breadcrumbs.breadcast.repository.bakery.BakeryRepository;
import com.breadcrumbs.breadcast.repository.course.CoursePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoursePartService {

    // CoursePartService는 코스 구성 요소와 관련된 비즈니스 로직을 처리
    // BakeryRepository를 통해 빵집의 유효성을 검증하고, CoursePartRepository를 통해 DB에 저장

    private final CoursePartRepository coursePartRepository;
    private final BakeryRepository bakeryRepository;

    public List<CoursePart> createCourseParts(Long courseId, List<CoursePart> courseParts) {
        CoursePart coursePart;

        /*
        -코스 구성 요소 엔티티 리스트를 받아 데이터베이스에 일괄 저장합니다.
        -courseParts 리스트를 순회하며 각 빵집 ID가 유효한지 BakeryRepository.findById를 통해 확인합니다.
        - 유효하지 않은 빵집 ID가 있다면 예외를 발생시킵니다.
        -유효성 검사를 통과한 CoursePart 엔티티 리스트를 coursePartRepository.saveAll을 호출하여 데이터베이스에 일괄 저장합니다.
        - 저장 후 엔티티 리스트를 반환합니다.
        */

        return null;
    }

    public List<CoursePart> updateCourseParts(Long courseId, List<CoursePart> courseParts) {
        /*
        -courseId에 연결된 모든 기존 CoursePart를 삭제하고, 새로운 구성 요소를 저장하는 메소드입니다.
        -courseParts 리스트를 순회하며 각 빵집 ID의 유효성을 bakeryRepository.findById를 통해 검증합니다.
        - coursePartRepository.deleteAllByCourseId를 호출하여 기존 데이터를 모두 삭제합니다.
        - 유효성 검사를 통과한 새로운 CoursePart 엔티티들을 coursePartRepository.saveAll을 호출하여 DB에 일괄 저장합니다.
        */

        return null;
    }
}
