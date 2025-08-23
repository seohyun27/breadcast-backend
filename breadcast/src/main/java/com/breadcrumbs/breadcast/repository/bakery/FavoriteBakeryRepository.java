package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.bakery.FavoriteBakery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteBakeryRepository {

    private final EntityManager em;

    public void save(FavoriteBakery favoriteBakery){
        em.persist(favoriteBakery);
    }

    public void delete(Long id) {
        FavoriteBakery favoriteBakery = em.find(FavoriteBakery.class, id);
        if (favoriteBakery != null) em.remove(favoriteBakery);
    }

    public FavoriteBakery findOne(Long id){
        return em.find(FavoriteBakery.class, id);
    }

}
