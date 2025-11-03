package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.dto.bakery.BakeryDetailResponse;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryRequest;
import com.breadcrumbs.breadcast.dto.bakery.SearchBakeryResponse;
import com.breadcrumbs.breadcast.repository.bakery.BakeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BakeryService {

    private final BakeryRepository bakeryRepository;

    public BakeryDetailResponse getBakeryDetail(Long bakeryId, Long memId) {
        /*
        -Bakery bakeryRepository.findByBakeryId(bakeryId) 호출
        - 호출한 Bakery를 DTO로 변환하여 컨트롤러로 반환
        */

        return null;
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


}
