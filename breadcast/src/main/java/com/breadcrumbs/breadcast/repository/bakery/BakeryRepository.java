package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakeryRepository extends JpaRepository<Bakery, Long> {
}

