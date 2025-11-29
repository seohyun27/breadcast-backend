package com.breadcrumbs.breadcast.domain.bakery.entity;

import com.breadcrumbs.breadcast.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)  // 날짜를 자동으로 저장하는 Auditing 기능 활성화
public class BakeryReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bakery_report_id")
    private long id;

    @Column(name = "report_text")
    private String text;

    @CreatedDate   // 엔티티가 처음 저장될 때 자동으로 값을 삽입
    @Column(name = "report_date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bakery_id")
    private Bakery bakery;


    /// 생성 메소드 ///
    public static BakeryReport createBakeryReport(String text, Member member, Bakery bakery) {
        BakeryReport bakeryReport = new BakeryReport();
        bakeryReport.text = text;
        bakeryReport.member = member;
        bakeryReport.bakery = bakery;
        return bakeryReport;
    }
}
