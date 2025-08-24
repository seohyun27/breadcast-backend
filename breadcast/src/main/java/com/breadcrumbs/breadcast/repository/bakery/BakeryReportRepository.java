package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BakeryReportRepository {

    private final EntityManager em;

    public void save(BakeryReport bakeryReport){
        em.persist(bakeryReport);
    }

    public void delete(Long id) {
        BakeryReport bakeryReport = em.find(BakeryReport.class, id);
        if (bakeryReport != null) em.remove(bakeryReport);
    }

    public BakeryReport findOne(Long id){
        return em.find(BakeryReport.class, id);
    }

    // Member 기준으로 빵집 제보 리스트 조회
    public List<BakeryReport> findByMember(Member member) {
        return em.createQuery("select br from BakeryReport br where br.member = :member ORDER BY br.date DESC",
                        BakeryReport.class)
                .setParameter("member", member)
                .getResultList();
    }
}
