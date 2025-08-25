package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BakeryReportRepository extends JpaRepository<BakeryReport, Long> {

    // Member 기준으로 빵집 제보 리스트 조회
    List<BakeryReport> findByMemberOrderByDateDesc(Member member);

    /*
    Spring Data JPA의 메서드 쿼리 기능을 이용 시 메서드 이름만으로 쿼리 생성 가능
    OrderByDateDesc :  'date' 필드를 기준으로 내림차순 정렬
    파라미터 member : 빵집 제보를 조회할 Member 객체
    리턴값 List<BakeryReport> : 해당 Member가 제보한 빵집 제보 리스트
     */
}