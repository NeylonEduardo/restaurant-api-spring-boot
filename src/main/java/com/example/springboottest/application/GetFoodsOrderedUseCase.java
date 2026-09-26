package com.example.springboottest.application;

import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetFoodsOrderedUseCase {
    private final List<FoodEntity> foodsList;

    public GetFoodsOrderedUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public List<FoodEntity> execute(String sortBy) {
        Comparator<FoodEntity> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(FoodEntity::name).thenComparing(FoodEntity::quantity);

            case "quantity" -> Comparator.comparing(FoodEntity::quantity).thenComparing(FoodEntity::price);

            case "price" -> Comparator.comparing(FoodEntity::price).thenComparing(FoodEntity::calories);

            case "calories" -> Comparator.comparing(FoodEntity::calories).thenComparing(FoodEntity::id);

            case "id" -> Comparator.comparing(FoodEntity::id);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return foodsList.stream()
                .sorted(comparator)
                .toList();
    }
}
