package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.Member;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
