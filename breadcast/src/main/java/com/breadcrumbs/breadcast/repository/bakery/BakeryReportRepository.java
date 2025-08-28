package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
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



    public Page<BakeryReport> findByBakeryIdOrderByCreatedAtDesc(Long bakeryId, Pageable pageable);
    /*
    bakeryId와 일치하는 BakeryReport 엔티티들을 최신순으로 정렬하여 반환합니다.
    Pageable 객체를 파라미터로 받습니다.
    Pageable은 페이지 번호, 페이지 크기, 정렬 방향 등을 모두 추상화하여 한 번에 처리합니다.
    */



    // createdAt으로 주어진 시각 이전에 생성된 모든 제보 글을 찾아 리스트로 반환
    public List<BakeryReport> findByCreatedAtBefore(LocalDateTime createdAt);

}