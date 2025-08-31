package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import com.breadcrumbs.breadcast.repository.bakery.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.repository.course.FavoriteCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteBakeryRepository favoriteBakeryRepository;
    private final FavoriteCourseRepository favoriteCourseRepository;

    @Transactional(readOnly = true)
    public List<Bakery> getFavoriteBakeries(Long memId){
        /*
        - favoriteBakeryRepository.findByMemberId(memId) 호출
        - 가게 사진 2장/가게 이름/가게 전화번호/주소
        - 조회된 엔티티 리스트를 FavoriteBakeryResponse DTO로 변환
        - 변환된 리스트를 컨트롤러로 반환
        - 현재 단계에서는 임시로 List<>를 반환하도록 작성함
        */

        return null;
    }

    public void addFavoriteBakery(Long bakeryId, Long memId){
        /*
        - 중복 여부 검사 : favoriteBakeryRepository.existsByMemberIdAndBakeryId()
        - 없으면 FavoriteBakery 엔티티 생성(생성 메소드 이용) 후 repository.save()
        */
    }

    public void deleteFavoriteBakery(Long bakeryId, Long memId){
        /*
        - 존재 여부 검사 favoriteBakeryRepository.existsByMemberIdAndBakeryId(memId, bakeryId);
        - 삭제 메소드 호출 favoriteBakeryRepository.deleteByMemberIdAndBakeryId(memId, bakeryId);
        */
    }

    public void addFavoriteCourse(Long courseId, Long memId){
        /*
        중복 여부 검사 favoriteCourseRepository.existsByMemberIdAndCourseId(memId, courseId);
        없으면 FavoriteCourse 엔티티 생성 후 repository.save()
        */
    }

    public void deleteFavoriteCourse(Long courseId, Long memId){
        /*
        - 존재 여부 검사 (existsByMemberIdAndCourseId)
        - favoriteCourseRepository.deleteByMemberIdAndCourseId(memId, courseId) 호출
        */
    }
}
