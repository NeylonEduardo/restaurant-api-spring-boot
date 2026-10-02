package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetFoodsOrderedUseCase {
    private final FoodRepository repository;

    public GetFoodsOrderedUseCase(FoodRepository repository) {
        this.repository = repository;
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

        List<FoodEntity> foodsList = repository.findAll();

        return foodsList.stream()
                .sorted(comparator)
                .map(FoodOutput::from)
                .toList();
    }
}
