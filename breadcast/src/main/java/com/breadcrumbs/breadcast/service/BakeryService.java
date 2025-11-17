package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReview;
import com.breadcrumbs.breadcast.dto.bakery.BakeryDetailResponse;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryRequest;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryResponse;
import com.breadcrumbs.breadcast.repository.bakery.BakeryRepository;
import com.breadcrumbs.breadcast.repository.bakery.BakeryReviewRepository;
import com.breadcrumbs.breadcast.repository.bakery.FavoriteBakeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
                .review_count(reviewCount)
                .favorite_count(favoriteCount)
                .build();

        //dto를 controller에게 반환
        return bakeryDetailResponse;
        /*
        -Bakery bakeryRepository.findByBakeryId(bakeryId) 호출
        - 호출한 Bakery를 DTO로 변환하여 컨트롤러로 반환
        */
    }

    //사용자가 빵집들을 검색할 수 있게 해주는 함수
    public List<SearchBakeryResponse> searchBakeries(SearchBakeryRequest request) {
        List<Bakery> bakeryList = bakeryRepository.findAll();
        List<SearchBakeryResponse> searchBakeryResponseList = new ArrayList<>();
        String keyword = request.getText(); // DTO에서 검색어를 가져옴
        int favoriteCount = 0;
        int reviewCount = 0;
        double rating = 0.0;

        if (keyword != null) {
            bakeryList = bakeryRepository.findByNameContainingIgnoreCase(keyword);
        }

        for (Bakery bakery : bakeryList) {
            favoriteCount = favoriteBakeryRepository.countByBakeryId(bakery.getId());
            reviewCount = bakeryReviewRepository.countByBakeryId(bakery.getId());
            rating = getAverageRating(bakery.getId());
            SearchBakeryResponse searchBakeryResponse = SearchBakeryResponse.builder()
                    .id(bakery.getId())
                    .name(bakery.getName())
                    .address(bakery.getAddress())
                    .photo1(bakery.getPhoto1())
                    .rating(rating)
                    .review_count(reviewCount)
                    .favorite_count(favoriteCount)
                    .build();

            searchBakeryResponseList.add(searchBakeryResponse);
        }

        searchBakeryResponseList.sort(createResponseComparator(request.getSortBy()));

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

    //사용자가 정렬 기준을 변경할 수 있게 해주는 함수
    private Comparator<SearchBakeryResponse> createResponseComparator(String sortBy) {

        if ("review".equals(sortBy)) {
            // 리뷰 순 (review_count 필드 기준 내림차순)
            return Comparator.comparing(
                    SearchBakeryResponse::getReview_count, Comparator.reverseOrder());
        }

        // 인기순 (favorite_count 필드 기준 내림차순) 및 기본값
        return Comparator.comparing(
                SearchBakeryResponse::getFavorite_count, Comparator.reverseOrder());
    }
}
