package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
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

    public List<FoodOutput> execute(String sortBy) {
        Comparator<FoodEntity> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(FoodEntity::getName).thenComparing(FoodEntity::getQuantity);

            case "quantity" -> Comparator.comparing(FoodEntity::getQuantity).thenComparing(FoodEntity::getPrice);

            case "price" -> Comparator.comparing(FoodEntity::getPrice).thenComparing(FoodEntity::getCalories);

            case "calories" -> Comparator.comparing(FoodEntity::getCalories).thenComparing(FoodEntity::getId);

            case "id" -> Comparator.comparing(FoodEntity::getId);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return foodsList.stream()
                .sorted(comparator)
                .map(FoodOutput::from)
                .toList();
    }
}
