package com.breadcrumbs.breadcast.repository.bakery;

import com.breadcrumbs.breadcast.domain.bakery.Bakery;
import com.breadcrumbs.breadcast.domain.bakery.BakeryReport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BakeryRepository extends JpaRepository<Bakery, Long> {

    // 1. 리뷰 수 우선 정렬 쿼리 (SELECT b, COUNT(r), COUNT(f) 반환)
    @Query("SELECT b, COUNT(DISTINCT r.id), COUNT(DISTINCT f.id) FROM Bakery b " +
            "LEFT JOIN BakeryReview r ON r.bakery = b " +
            "LEFT JOIN FavoriteBakery f ON f.bakery = b " +
            "WHERE REPLACE(b.name, ' ', '') LIKE CONCAT('%', :cleanedSearchTerm, '%') " +
            "GROUP BY b.id, b.name, b.address, b.phone, b.latitude, b.longitude, b.URL, b.photo1, b.photo2 " +
            "ORDER BY COUNT(DISTINCT r.id) DESC, COUNT(DISTINCT f.id) DESC")
        // 반환 타입을 List<Object[]>로 변경
    List<Object[]> findByNameIgnoringSpacesAndSortReview(@Param("cleanedSearchTerm") String cleanedSearchTerm);

    // 2. 스크랩 수 우선 정렬 쿼리 (SELECT b, COUNT(r), COUNT(f) 반환)
    @Query("SELECT b, COUNT(DISTINCT r.id), COUNT(DISTINCT f.id) FROM Bakery b " +
            "LEFT JOIN BakeryReview r ON r.bakery = b " +
            "LEFT JOIN FavoriteBakery f ON f.bakery = b " +
            "WHERE REPLACE(b.name, ' ', '') LIKE CONCAT('%', :cleanedSearchTerm, '%') " +
            "GROUP BY b.id, b.name, b.address, b.phone, b.latitude, b.longitude, b.URL, b.photo1, b.photo2 " +
            "ORDER BY COUNT(DISTINCT f.id) DESC, COUNT(DISTINCT r.id) DESC")
    // 반환 타입을 List<Object[]>로 변경
    List<Object[]> findByNameIgnoringSpacesAndSortFavorite(@Param("cleanedSearchTerm") String cleanedSearchTerm);
}

