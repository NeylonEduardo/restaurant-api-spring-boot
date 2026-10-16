package com.neylon.restaurantapi.dto;

import com.neylon.restaurantapi.enums.CategoryType;
import com.neylon.restaurantapi.model.FoodEntity;

import java.math.BigDecimal;

public record FoodOutput(String id, String name, BigDecimal price, float calories, int quantity,
                         CategoryType category) {

    public static FoodOutput from(FoodEntity entity) {
        return new FoodOutput(
                entity.getId().id().toString(),
                entity.getName(),
                entity.getPrice(),
                entity.getCalories(),
                entity.getQuantity(),
                entity.getCategory().getName()
        );
    }
}
