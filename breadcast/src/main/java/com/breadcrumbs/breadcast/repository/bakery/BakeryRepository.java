package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BakeryRepository {
    private final EntityManager em;

    public void save(Bakery bakery){
        em.persist(bakery);
    }

    public void delete(Long id) {
        Bakery bakery = em.find(Bakery.class, id);
        if (bakery != null) em.remove(bakery);
    }

    public Bakery findOne(Long id){
        return em.find(Bakery.class, id);
    }
}
