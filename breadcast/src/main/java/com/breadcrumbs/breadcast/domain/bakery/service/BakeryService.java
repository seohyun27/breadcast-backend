package com.breadcrumbs.breadcast.domain.bakery.service;

import com.breadcrumbs.breadcast.domain.bakery.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.domain.bakery.dto.SearchBakeryResponse;
import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
import com.breadcrumbs.breadcast.domain.favorite.entity.FavoriteBakery;
import com.breadcrumbs.breadcast.domain.favorite.repository.FavoriteBakeryRepository;
import com.breadcrumbs.breadcast.domain.review.entity.BakeryReview;
import com.breadcrumbs.breadcast.domain.review.repository.BakeryReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BakeryService {

    private final BakeryRepository bakeryRepository;
    private final BakeryReviewRepository bakeryReviewRepository;
    private final FavoriteBakeryRepository favoriteBakeryRepository;

    public BakeryDetailResponse getBakeryDetail(Long bakeryId, Long memId) {
        //bakery entity와 스크랩 수, 빵집 리뷰 수, 평균 별점을 찾는다.
        Bakery bakery = bakeryRepository.findById(bakeryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다. ID: " + bakeryId));
        int favoriteCount = favoriteBakeryRepository.countByBakeryId(bakeryId);
        int reviewCount = bakeryReviewRepository.countByBakeryId(bakeryId);
        double rating = getAverageRating(bakeryId);
        long favoriteId;
        boolean isFavorited = false;

        //사용자가 좋아요한 빵집인지 확인
        if(memId != null) {
            List<FavoriteBakery> favoriteBakeryList = favoriteBakeryRepository.findByMemberId(memId);
            for (FavoriteBakery favoriteBakery : favoriteBakeryList) {
                favoriteId = favoriteBakery.getBakery().getId();
                if (favoriteId == bakeryId) {
                    isFavorited = true;
                    break; // 찾았으면 더 이상 루프를 돌 필요가 없습니다.
                }
            }
        }

        //찾은 정보들을 BakeryDetailResponse로 변환
        BakeryDetailResponse bakeryDetailResponse = BakeryDetailResponse.builder()
                .id(bakery.getId())
                .name(bakery.getName())
                .address(bakery.getAddress())
                .phone(bakery.getPhone())
                .latitude(bakery.getLatitude())
                .longitude(bakery.getLongitude())
                .URL(bakery.getURL())
                .photo1(bakery.getPhoto1())
                .photo2(bakery.getPhoto2())
                .rating(rating)
                .reviewCount(reviewCount)
                .favoriteCount(favoriteCount)
                .isFavorited(isFavorited)
                .operatingHours(bakery.getOperatingHours())
                .inform(bakery.getText())
                .build();

        //dto를 controller에게 반환
        return bakeryDetailResponse;
        /*
        -Bakery bakeryRepository.findByBakeryId(bakeryId) 호출
        - 호출한 Bakery를 DTO로 변환하여 컨트롤러로 반환
        */
    }

    //사용자가 빵집들을 검색할 수 있게 해주는 함수
    public List<SearchBakeryResponse> searchBakeries(String keyword, String sort) {
        List<SearchBakeryResponse> searchBakeryResponseList = new ArrayList<>();
        List<Object[]> bakeryList; // 쿼리 결과는 Object[] 목록으로 받음
        double rating = 0.0;

        if(keyword == null){
            bakeryList = bakeryRepository.findAllPopularBakeries();
        }
        else {
            String cleanedSearchTerm = keyword.replaceAll("\\s+", "");

            // 문자열을 대소문자 구분 없이 비교
            if ("REVIEW".equalsIgnoreCase(sort)) {
                // 이름 공백 무시 검색, 리뷰순 정렬 쿼리 호출
                bakeryList = bakeryRepository.findByNameIgnoringSpacesAndSortReview(cleanedSearchTerm);
            } else {
                // "POPULAR" 또는 기타 문자열은 스크랩순(인기순)으로 처리
                bakeryList = bakeryRepository.findByNameIgnoringSpacesAndSortFavorite(cleanedSearchTerm);
            }
        }

        for (Object[] row : bakeryList) {
            Bakery bakery = (Bakery) row[0];

            // 쿼리에서 계산된 COUNT 값을 사용 (Long -> int 변환)
            Long reviewCountLong = (Long) row[1];
            Long favoriteCountLong = (Long) row[2];
            rating = getAverageRating(bakery.getId());

            SearchBakeryResponse searchBakeryResponse = SearchBakeryResponse.builder()
                    .id(bakery.getId())
                    .name(bakery.getName())
                    .address(bakery.getAddress())
                    .photo1(bakery.getPhoto1())
                    .rating(rating)
                    .reviewCount(reviewCountLong.intValue())
                    .favoriteCount(favoriteCountLong.intValue())
                    .build();

            searchBakeryResponseList.add(searchBakeryResponse);
        }

        return searchBakeryResponseList;

        /*
        -searchBakeries(request) 호출
        -DTO로 변환하여 컨트롤러로 반환
        List<Bakery> searchBakeries(BakerySearchRequest request)
        -String keyword = request.getKeyword()로 받아온 후 단어별로 나눠 가게명/지역/메뉴명 검색 메소드 호출 # 무조건 한 단어당 한 메소드만 호출... 구체화 필요
        -Sort 객체로 좋아요 순 정렬
         */
    }

    //평균 별점을 구하는 함수(service 내부에서 쓸 것이라 private로 선언)
    private double getAverageRating(Long bakeryId){
        List<BakeryReview> bakeryReviews = bakeryReviewRepository.findByBakeryId(bakeryId);
        int reviewCount = bakeryReviewRepository.countByBakeryId(bakeryId);
        double ratingSum = 0.0;

        if (bakeryReviews.isEmpty()) {
            return ratingSum;
        }

        for (BakeryReview bakeryreview : bakeryReviews) {
            ratingSum += bakeryreview.getRating();
        }

        double averageResult = ratingSum / (double) reviewCount;

        return averageResult;
    }
}
