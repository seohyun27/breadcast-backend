package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import com.breadcrumbs.breadcast.domain.course.CourseReview;
import com.breadcrumbs.breadcast.domain.menu.MenuReview;
import com.breadcrumbs.breadcast.dto.BakeryReviewAddRequest;
import com.breadcrumbs.breadcast.dto.BakeryReviewResponse;
import com.breadcrumbs.breadcast.dto.BakeryReviewUpdateRequest;
import com.breadcrumbs.breadcast.repository.bakery.BakeryReviewRepository;
import com.breadcrumbs.breadcast.repository.course.CourseRepository;
import com.breadcrumbs.breadcast.repository.course.CourseReviewRepository;
import com.breadcrumbs.breadcast.repository.menu.MenuRepository;
import com.breadcrumbs.breadcast.repository.menu.MenuReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 추후 모든 클래스에 걸쳐 사용하지 않는 import 문은 삭제할 것


@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final BakeryReviewRepository bakeryReviewRepository;

    private final MenuReviewRepository menuReviewRepository;
    private final MenuRepository menuRepository;

    private final CourseReviewRepository courseReviewRepository;
    private final CourseRepository courseRepository;

    // 사용자 인증 및 권한 확인 Service 추가로 필요

    public BakeryReview addBakeryReview(BakeryReviewAddRequest request) {
        /*
        -DTO 유효성 확인:내용 존재
        -createBakeryReview(request) 호출하여 엔티티 생성
        - bakeryReviewRepository.save(bakeryReview) 호출하여 DB에 저장
        BakeryReview createReview (AddBakeryReviewRequest request) #변동 가능성
         */

        return null;
    }

    public BakeryReview updateBakeryReview(BakeryReviewUpdateRequest request) {
        /*
        -DTO 유효성 확인:내용 존재
        -bakeryReview = bakeryReviewRepository.findById(request.getId()) 호출
        - 해당 리뷰 ID가 없으면(삭제 등) 예외처리(.orElseThrow())
        -BakeryReview.update(double rating, String text, String photo) 호출하여 수정 (더티 체크 적용됨)
         */

        return null;
    }

    public void deleteBakeryReview(Long bakeryRevieweId) {
        /*
        -bakeryReviewRepository.findById(bakeryReviewId) 호출하여 존재 여부 확인
        -해당 리뷰 ID가 없으면 예외처리 (.orElseThrow())
        -bakeryReviewRepository.deleteById(bakeryReviewId)
        */
    }


    @Transactional(readOnly = true)
    public BakeryReviewResponse getBakeryReviews(Long bakeryId) {
        /*
        -List <BakeryReview> bakeryReviewRepository.findAll(bakeryId) 호출
        - DTO로 변환하여 컨트롤러로 반환
        */

        return null;
    }

    @Transactional(readOnly = true)
    public List<BakeryReview> getMyBakeryReview(Long memId) {
        /*
        - bakeryReviewRepository.findByMemberId(memId); 를 호출해 빵집 리뷰 목록을 가져온다
        - 해당하는 모든 리뷰에 대해 가게 사진 1 장 / 가게 이름 / 리뷰 내용을 DTO로 묶어 반환
        */

        return null;
    }

    public MenuReview addMenuReview(Long memId, MenuReview menuReview) {
        /*
        -리뷰 엔티티를 받아 데이터베이스에 저장합니다.저장된 MenuReview 엔티티를 반환합니다.
        -menuRepository.findById() 를 호출하여 리뷰를 작성할 메뉴가 실제로 존재하는지 확인합니다.
        -존재가 확인되면, menuReviewRepository.save를 호출하여 DB에 저장합니다.
        - 저장 후 MenuReview 엔티티를 반환합니다.
        */

        return null;
    }

    public MenuReview updateMenuReview(Long memId, Long reviewId, double rating, String text) {
        /*
        - 해당 리뷰가 존재하는지 확인하고 존재하지 않으면 예외를 발생시킵니다.
        - 만약 있다면 조회된 리뷰 엔티티의 작성자 ID와 memId가 일치하는지 확인합니다.
        - 일치하지 않으면 수정 권한이 없으므로 예외를 발생시킵니다.
        - 검증이 완료되면 멤버 ID를 기준으로 DB 내 영속성 객체를 가져온다.
        - 가져온 객체에 MenuReview.update 메소드를 이용해 조회된 리뷰 엔티티에 새로운 내용을 반영합니다.
        - 수정된 MenuReview 엔티티를 반환합니다.
        */

        return null;
    }

    public void deleteMenuReview(Long reviewId, Long memId){
        /*
        - reviewId와 memId를 사용하여 리뷰를 삭제합니다.
        - menuReviewRepository.findById를 호출하여 해당 리뷰가 존재하는지 확인합니다.
        - 조회된 리뷰 엔티티의 작성자 ID가 memId와 일치하는지 확인하여 삭제 권한을 검증합니다.
        - 권한 확인이 끝나면 menuReviewRepository.deleteById를 호출하여 데이터베이스에서 리뷰를 삭제합니다.
        - 삭제에 성공하면 void 타입으로 처리됩니다.
        */
    }

    @Transactional(readOnly = true)
    public List<MenuReview> getMyMenuReview(Long memId) {
        /*
        -menuReviewRepository.findByMemberId(memId); 를 호출해 메뉴 리뷰 목록을 가져온다
        - 해당하는 모든 리뷰에 대해 가게 이름 / 메뉴 이름 / 리뷰 내용을 DTO로 묶어 리턴한다
        */

        return null;
    }

    public CourseReview addCourseReview(CourseReview courseReview) {
        /*
        -리뷰 엔티티를 받아 데이터베이스에 저장합니다.
        -courseRepository.findById(courseReview.getCourse().getId()) 를 호출하여 리뷰를 작성할 코스가 실제로 존재하는지 확인합니다.
        -코스가 존재하지 않으면 예외를 발생시키거나 null을 반환하여 리뷰 저장을 중단합니다.
        - 코스가 존재할 경우, courseReviewRepository.save를 호출하여 데이터베이스에 리뷰를 저장합니다.
        - 저장 후 CourseReview 엔티티를 반환합니다.
        */

        return null;
    }

    public CourseReview updateCourseReview(Long courseReviewId, Long memId, String text) {
        /*
        - 해당 리뷰가 존재하는지 확인합니다.
        - 존재하지 않으면 예외를 발생시킵니다.
        - 조회된 리뷰 엔티티의 작성자 ID가 memId와 일치하는지 확인합니다.
        - 일치하지 않으면 수정 권한이 없으므로 예외를 발생시킵니다.
        - 검증이 끝나면 courseReview.update를 통해 조회된 리뷰 엔티티에 새로운 내용을 반영합니다.
        - 수정된 CourseReview 엔티티를 반환합니다.
        */

        return null;
    }

    public void deleteCourseReview(Long courseReviewId, Long memId) {
       /*
        설명 : 특정 reviewId에 해당하는 리뷰를 삭제하는 메소드입니다.
        -courseReviewRepository.findById를 호출하여 해당 리뷰가 존재하는지 확인합니다.
        - memId가 작성자와 일치하는지 검증합니다.
        - 권한이 없으면 삭제를 진행하지 않습니다.
        -권한 확인이 완료되면, courseReviewRepository.deleteById를 호출하여 데이터베이스에서 리뷰를 삭제합니다.
        */
    }

    public List<CourseReview> getMyCourseReview(Long memId) {
        /*
        - courseReviewRepository.findByMemberId(memId)를 호출해 메뉴 리뷰 목록을 가져온다
        - 해당하는 모든 리뷰에 대해 루트 작성자 닉네임/루트 제목/내 닉네임/리뷰 내용을 DTO로 묶어 리턴한다
        */

        return null;
    }
}
