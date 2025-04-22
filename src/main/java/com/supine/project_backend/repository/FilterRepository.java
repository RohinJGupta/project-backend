package com.supine.project_backend.repository;

import com.supine.project_backend.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface FilterRepository extends JpaRepository<PortfolioItem, Long> {

    @Query(value = """
            SELECT i FROM PortfolioItem i
            WHERE (LOWER(:search) IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.description) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:category IS NULL OR i.category = :category)
            AND (:startDate IS NULL OR i.projectDate >= :startDate)
            AND (:endDate IS NULL OR i.projectDate <= :endDate)
            AND (:minCost IS NULL OR i.projectCost >= :minCost)
            AND (:maxCost IS NULL OR i.projectCost <= :maxCost)
            AND (:minDuration IS NULL OR i.duration >= :minDuration)
            AND (:maxDuration IS NULL OR i.duration <= :maxDuration)
            """)
    List<PortfolioItem> findFilteredItems(
            @Param("search") String search,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minCost") Double minCost,
            @Param("maxCost") Double maxCost,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration);

    @Query(value = """
            SELECT
                AVG(i.projectCost) as averageCost,
                AVG(i.duration) as averageDuration,
                COUNT(i) as totalItems,
                MIN(i.projectCost) as minCost,
                MAX(i.projectCost) as maxCost
            FROM PortfolioItem i
            WHERE (LOWER(:search) IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.description) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:category IS NULL OR i.category = :category)
            AND (:startDate IS NULL OR i.projectDate >= :startDate)
            AND (:endDate IS NULL OR i.projectDate <= :endDate)
            AND (:minCost IS NULL OR i.projectCost >= :minCost)
            AND (:maxCost IS NULL OR i.projectCost <= :maxCost)
            AND (:minDuration IS NULL OR i.duration >= :minDuration)
            AND (:maxDuration IS NULL OR i.duration <= :maxDuration)
            """)
    Map<String, Object> getFilteredItemsStatistics(
            @Param("search") String search,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minCost") Double minCost,
            @Param("maxCost") Double maxCost,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration);

    @Query(value = """
            SELECT
                COALESCE(i.category, 'Uncategorized') as category,
                COUNT(i) AS item_count
            FROM PortfolioItem i
            WHERE (LOWER(:search) IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(i.description) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:category IS NULL OR i.category = :category)
            AND (:startDate IS NULL OR i.projectDate >= :startDate)
            AND (:endDate IS NULL OR i.projectDate <= :endDate)
            AND (:minCost IS NULL OR i.projectCost >= :minCost)
            AND (:maxCost IS NULL OR i.projectCost <= :maxCost)
            AND (:minDuration IS NULL OR i.duration >= :minDuration)
            AND (:maxDuration IS NULL OR i.duration <= :maxDuration)
            GROUP BY COALESCE(i.category, 'Uncategorized')
            ORDER BY item_count DESC
                
            """)
    List<Map<String, Object>> getCategoryDistribution(
            @Param("search") String search,
            @Param("category") String category,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("minCost") Double minCost,
            @Param("maxCost") Double maxCost,
            @Param("minDuration") Integer minDuration,
            @Param("maxDuration") Integer maxDuration);
}