package com.breadcrumbs.breadcast.domain.favorite.service;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteBakeriesResponse;
import com.breadcrumbs.breadcast.domain.favorite.dto.GetFavoriteCoursesResponse;
import com.breadcrumbs.breadcast.domain.favorite.entity.FavoriteBakery;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteCourseRepository;
import com.breadcrumbs.breadcast.domain.member.entity.Member;
import com.breadcrumbs.breadcast.domain.member.repository.MemberRepository;
import com.breadcrumbs.breadcast.global.apiPayload.exception.GeneralException;
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
    private final MemberRepository memberRepository;
    private final BakeryRepository bakeryRepository;

    @Transactional(readOnly = true)
    public List<GetFavoriteBakeriesResponse> getFavoriteBakeries(Long memberId){
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
        // 1. 사용자가 로그인한 자인지 확인
        if (memId == null) {
            throw new GeneralException("로그인한 사용자만 관심 가게를 등록할 수 있습니다.");
        }

        // 2. 중복 여부 검사
        if (favoriteBakeryRepository.existsByMemberIdAndBakeryId(memId, bakeryId)) {
            throw new GeneralException("이미 관심 가게로 등록된 빵집입니다.");
        }

        // 3. Member 및 Bakery 엔티티 조회
        Member member = memberRepository.findById(memId)
                .orElseThrow(() -> new GeneralException("사용자 정보를 찾을 수 없습니다."));

        Bakery bakery = bakeryRepository.findById(bakeryId)
                .orElseThrow(() -> new GeneralException("빵집 정보를 찾을 수 없습니다."));

        // 4. FavoriteBakery 엔티티 생성 및 저장
        FavoriteBakery favoriteBakery = FavoriteBakery.createFavoriteBakery(member, bakery);
        favoriteBakeryRepository.save(favoriteBakery);

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

    @Transactional(readOnly = true)
    public List<GetFavoriteCoursesResponse> findFavoriteCourses(Long memId){
        return null;
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
