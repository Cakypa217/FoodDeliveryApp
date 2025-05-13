package ru.aston.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aston.model.entity.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query("""
            SELECT d FROM Dish d
            JOIN d.category c
            JOIN d.ingredients i
            WHERE (:category IS NULL OR c.name = :category)
            AND (:ingredient IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :ingredient, '%')))
            GROUP BY d.id
            ORDER BY d.name ASC
            """)
    List<Dish> searchByCriteria(
            @Param("category") String category,
            @Param("ingredient") String ingredient,
            Pageable pageable
    );

    @Query("""
            SELECT d FROM Dish d
            WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    List<Dish> findBySearch(@Param("query") String query);
}