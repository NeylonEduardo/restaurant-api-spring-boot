package com.neylon.restaurantapi.repository;

import com.neylon.restaurantapi.domain.FoodId;
import com.neylon.restaurantapi.enums.CategoryType;
import com.neylon.restaurantapi.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodEntity, FoodId> {
    Optional<FoodEntity> findByNameIgnoreCase(String name);

    List<FoodEntity> findByPriceLessThanEqual(BigDecimal maxPrice);

    List<FoodEntity> findByCategoryName(CategoryType categoryName);
}
