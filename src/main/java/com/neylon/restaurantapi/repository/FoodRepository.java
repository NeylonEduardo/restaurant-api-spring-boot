package com.neylon.restaurantapi.repository;

import com.neylon.restaurantapi.domain.FoodId;
import com.neylon.restaurantapi.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface FoodRepository extends JpaRepository<FoodEntity, FoodId> {
    List<FoodEntity> findByNameIgnoreCase(String name);

    List<FoodEntity> findByPriceLessThanEqual(BigDecimal maxPrice);
}
