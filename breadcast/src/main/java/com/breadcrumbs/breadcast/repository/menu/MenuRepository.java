package com.breadcrumbs.breadcast.repository.menu;

import com.breadcrumbs.breadcast.domain.menu.Menu;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final EntityManager em;

    public void save(Menu menu){
        em.persist(menu);
    }

    public void delete(Long id) {
        Menu menu = em.find(Menu.class, id);
        if (menu != null) em.remove(menu);
    }

    public Menu findOne(Long id){
        return em.find(Menu.class, id);
    }

}
