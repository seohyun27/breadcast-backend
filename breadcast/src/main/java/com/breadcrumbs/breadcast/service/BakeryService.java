package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.dto.BakeryDetailResponse;
import com.breadcrumbs.breadcast.dto.BakerySearchRequest;
import com.breadcrumbs.breadcast.dto.BakerySearchResponse;
import com.breadcrumbs.breadcast.repository.bakery.BakeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BakeryService {

    private final BakeryRepository bakeryRepository;

    BakeryDetailResponse getBakeryDetail(Long bakeryId) {
        /*
        -Bakery bakeryRepository.findByBakeryId(bakeryId) 호출
        - 호출한 Bakery를 DTO로 변환하여 컨트롤러로 반환
        */

        return null;
    }

    public BakerySearchResponse searchBakeries(BakerySearchRequest request) {
        /*
        -searchBakeries(request) 호출
        -DTO로 변환하여 컨트롤러로 반환
        List<Bakery> searchBakeries(BakerySearchRequest request)
        -String keyword = request.getKeyword()로 받아온 후 단어별로 나눠 가게명/지역/메뉴명 검색 메소드 호출 # 무조건 한 단어당 한 메소드만 호출... 구체화 필요
        -Sort 객체로 좋아요 순 정렬
         */

        return null;
    }


}
