package com.breadcrumbs.breadcast.domain.bakery.repository;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.entity.BakeryReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BakeryRepository extends JpaRepository<Bakery, Long> {
}

