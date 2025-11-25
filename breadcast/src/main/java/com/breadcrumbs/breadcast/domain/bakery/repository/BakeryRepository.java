package com.breadcrumbs.breadcast.domain.bakery.repository;

import com.breadcrumbs.breadcast.domain.bakery.entity.Bakery;
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

    // 3. 모든 가게를 인기순(스크랩 수)으로 정렬 쿼리 (검색어 없음)
    @Query("SELECT b, COUNT(DISTINCT r.id), COUNT(DISTINCT f.id) FROM Bakery b " +
            "LEFT JOIN BakeryReview r ON r.bakery = b " +
            "LEFT JOIN FavoriteBakery f ON f.bakery = b " +
            // WHERE 절을 삭제하여 모든 가게를 대상으로 합니다.
            "GROUP BY b.id, b.name, b.address, b.phone, b.latitude, b.longitude, b.URL, b.photo1, b.photo2 " +
            // 스크랩 수(f) 내림차순, 리뷰 수(r) 내림차순으로 정렬합니다.
            "ORDER BY COUNT(DISTINCT f.id) DESC, COUNT(DISTINCT r.id) DESC")
    List<Object[]> findAllPopularBakeries(); // 새로운 메서드 이름
}

