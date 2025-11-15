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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        int favorite_count = favoriteBakeryRepository.countByBakeryId(bakeryId);
        int review_count = bakeryReviewRepository.countByBakeryId(bakeryId);
        double rating = getAverageRating(bakeryId,review_count);

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
                .review_count(review_count)
                .favorite_count(favorite_count)
                .build();

        //dto를 controller에게 반환
        return bakeryDetailResponse;
        /*
        -Bakery bakeryRepository.findByBakeryId(bakeryId) 호출
        - 호출한 Bakery를 DTO로 변환하여 컨트롤러로 반환
        */
    }

    public List<SearchBakeryResponse> searchBakeries(SearchBakeryRequest request) {
        /*
        -searchBakeries(request) 호출
        -DTO로 변환하여 컨트롤러로 반환
        List<Bakery> searchBakeries(BakerySearchRequest request)
        -String keyword = request.getKeyword()로 받아온 후 단어별로 나눠 가게명/지역/메뉴명 검색 메소드 호출 # 무조건 한 단어당 한 메소드만 호출... 구체화 필요
        -Sort 객체로 좋아요 순 정렬
         */

        return null;
    }

    //평균 별점을 구하는 함수(service 내부에서 쓸 것이라 private로 선언)
    private double getAverageRating(Long bakeryId, int reviewCount){
        List<BakeryReview> bakeryReviews = bakeryReviewRepository.findByBakeryId(bakeryId);
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
