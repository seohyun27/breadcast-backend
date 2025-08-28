package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.menu.MenuReview;
import com.breadcrumbs.breadcast.repository.menu.MenuRepository;
import com.breadcrumbs.breadcast.repository.menu.MenuReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 추후 모든 클래스에 걸쳐 사용하지 않는 import 문은 삭제할 것


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private MenuReviewRepository menuReviewRepository;
    private MenuRepository menuRepository;
    private MenuReview menuReview;

    // 사용자 인증 및 권한 확인 Service 추가로 필요


    public MenuReview addMenuReview(Long userId, MenuReview menuReview) {
        /*
        -리뷰 엔티티를 받아 데이터베이스에 저장합니다.저장된 MenuReview 엔티티를 반환합니다.
        -menuRepository.findById() 를 호출하여 리뷰를 작성할 메뉴가 실제로 존재하는지 확인합니다.
        -존재가 확인되면, menuReviewRepository.save를 호출하여 DB에 저장합니다.
        - 저장 후 MenuReview 엔티티를 반환합니다.
        */

        return null;
    }

    public MenuReview updateMenuReview(Long reviewId, MenuReview updatedMenuReview, Long userId) {
        /*
        -reviewId에 해당하는 리뷰를 updatedMenuReview 엔티티의 내용으로 수정하는 메소드입니다.
        - menuReviewRepository.findById를 호출하여 해당 리뷰가 존재하는지 확인합니다.
        - 존재하지 않으면 예외를 발생시킵니다.
        - 조회된 리뷰 엔티티의 작성자 ID와 userId가 일치하는지 확인합니다.
        - 일치하지 않으면 수정 권한이 없으므로 예외를 발생시킵니다.
        -검증이 완료되면, 조회된 리뷰 엔티티에 updatedMenuReview의 새로운 내용을 반영합니다.
        -변경된 엔티티를 menuReviewRepository.save 메소드를 통해 데이터베이스에 저장합니다.
        - 수정된 MenuReview 엔티티를 반환합니다.
        */

        return null;
    }

    public void deleteMenuReview(Long reviewId, Long userId){
        /*
        - reviewId와 userId를 사용하여 리뷰를 삭제합니다.
        - menuReviewRepository.findById를 호출하여 해당 리뷰가 존재하는지 확인합니다.
        - 조회된 리뷰 엔티티의 작성자 ID가 userId와 일치하는지 확인하여 삭제 권한을 검증합니다.
        - 권한 확인이 끝나면 menuReviewRepository.deleteById를 호출하여 데이터베이스에서 리뷰를 삭제합니다.
        - 삭제에 성공하면 void 타입으로 처리됩니다.
        */
    }



}
