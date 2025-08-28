package com.breadcrumbs.breadcast.service;

import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import com.breadcrumbs.breadcast.repository.bakery.BakeryReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

    private BakeryReportRepository bakeryReportRepository;

    // 사용자 인증 및 권한 확인 Service 추가로 필요



    public List<BakeryReport> getReports(Long bakeryId, Long userId, int page, int size) {
        /*
        -bakeryId에 해당하는 제보 목록을 조회하고, userId에 따라 추가 정보를 포함하여 엔티티 리스트를 반환합니다.
        - bakeryReportRepository.findByBakeryIdOrderByCreatedAtDesc를 호출하여 특정 가게에 대한 제보 엔티티 목록을 가져옵니다.
        -페이징 처리를 위해 page와 size를 사용하여 offset과 limit을 계산합니다.
        -가져온 BakeryReport 엔티티 리스트를 살피며, 로그인된 사용자(userId) 가 해당 제보의 작성자인지를 확인하는 로직을 추가합니다. (이 정보는 엔티티 내에 필드로 추가되거나, 별도의
        데이터로 관리해도 될 듯, 추가해야 할 데이터들임)
        -수정된 BakeryReport 엔티티 리스트를 반환합니다.
        */

        return null;
    }

}
