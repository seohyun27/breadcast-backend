package com.breadcrumbs.breadcast.domain.course.service;

import com.breadcrumbs.breadcast.domain.course.CoursePart;
import com.breadcrumbs.breadcast.dto.course.CoursePartRequest;
import com.breadcrumbs.breadcast.dto.course.CoursePartResponse;
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
    private final BakeryRepository bakeryRepository;    // 코스 파트에 적힌 빵집이 존재하는지 확인하는 용도

    public List<CoursePartResponse> createCourseParts(Long courseId, List<CoursePartRequest> courseParts) {
        CoursePart coursePart;

        return null;
    }

    public List<CoursePartResponse> updateCourseParts(Long courseId, List<CoursePartRequest> courseParts) {

        return null;
    }
}
