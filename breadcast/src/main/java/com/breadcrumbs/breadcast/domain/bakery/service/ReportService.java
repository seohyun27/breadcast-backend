package com.breadcrumbs.breadcast.domain.bakery.service;

import com.breadcrumbs.breadcast.domain.bakery.dto.report.AddReportRequest;
import com.breadcrumbs.breadcast.domain.bakery.dto.report.ReportsResponse;
import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.entity.BakeryReport;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryReportRepository;
import com.breadcrumbs.breadcast.domain.bakery.repository.BakeryRepository;
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
public class ReportService {

    private final BakeryReportRepository bakeryReportRepository;
    private final BakeryRepository bakeryRepository;
    private final MemberRepository memberRepository;

    // 사용자 인증 및 권한 확인 Service 추가로 필요


    @Transactional(readOnly = true)
    public List<ReportsResponse> getReports(Long bakeryId, Long memId) {
        /*
        -bakeryId에 해당하는 제보 목록을 조회하고, memId에 따라 추가 정보를 포함하여 엔티티 리스트를 반환합니다.
        - bakeryReportRepository.findByBakeryIdOrderByCreatedAtDesc를 호출하여 특정 가게에 대한 제보 엔티티 목록을 가져옵니다.
        -페이징 처리를 위해 page와 size를 사용하여 offset과 limit을 계산합니다.
        -가져온 BakeryReport 엔티티 리스트를 살피며, 로그인된 사용자(memId) 가 해당 제보의 작성자인지를 확인하는 로직을 추가합니다. (이 정보는 엔티티 내에 필드로 추가되거나, 별도의
        데이터로 관리해도 될 듯, 추가해야 할 데이터들임)
        -수정된 BakeryReport 엔티티 리스트를 반환합니다.
        */

        return null;
    }

    public ReportsResponse addReport(Long bakeryId, Long memId,
                                     AddReportRequest request) {
        // 1. 사용자 인증 확인
        if (memId == null) {
            throw new GeneralException("로그인한 사용자만 제보를 작성할 수 있습니다.");
        }

        // 2. Member 조회
        Member member = memberRepository.findById(memId)
                .orElseThrow(() -> new GeneralException("사용자 정보를 찾을 수 없습니다."));

        // 3. Bakery 조회
        Bakery bakery = bakeryRepository.findById(bakeryId)
                .orElseThrow(() -> new GeneralException("빵집 정보를 찾을 수 없습니다."));

        // 4. BakeryReport 엔티티 생성 및 저장
        BakeryReport bakeryReport = BakeryReport.createBakeryReport(
                request.getText(),
                member,
                bakery
        );
        bakeryReportRepository.save(bakeryReport);

        // 5. Response DTO 생성 및 반환
        return ReportsResponse.builder()
                .bakeryReportId(bakeryReport.getId())
                .title(request.getText())
                .name(member.getNickname())
                .date(bakeryReport.getDate())
                .isMine(true)  // 방금 작성한 글이므로 true
                .build();
    }


    /// 유저가 제보글을 직접 삭제하는 경우 ///
    public void deleteBakeryReport(Long bakeryReportId, Long memId) {
        /*
        -bakeryReportId와 memId를 사용하여 제보를 삭제합니다.
        -bakeryReportRepository.findById를 호출하여 해당 제보가 존재하는지 확인합니다.
        - 조회된 제보 엔티티의 작성자 ID가 memId와 일치하는지 확인하여 삭제 권한을 검증합니다.
        -권한 확인이 끝나면 bakeryReportRepository.deleteById를 호출하여 데이터베이스에서 제보를 삭제합니다.
        - 삭제에 성공하면 void 타입으로 처리됩니다.
        */
    }


    /// 게시 기간이 지나 시스템이 제보글을 자동으로 삭제하는 경우 ///
    public void cleanupExpiredReports() {
        /*
        -현재 시간으로부터 일주일 이상 지난 모든 제보 글을 찾아 데이터베이스에서 삭제하는 메소드입니다.
        - LocalDateTime.now().minusWeeks(1) 을 사용하여 현재 시간으로부터 정확히 일주일 전의 날짜를 계산합니다.
        - bakeryReportRepository.findByCreatedAtBefore 메소드를 호출하여 계산된 날짜보다 이전에 작성된 모든 제보 목록을 가져옵니다.
        -가져온 제보 목록을 bakeryReportRepository.deleteAll 메소드를 호출하여 데이터베이스에서 모두 삭제합니다.
        */
    }
}
