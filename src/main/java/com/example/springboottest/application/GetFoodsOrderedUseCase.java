package com.example.springboottest.application;

import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetFoodsOrderedUseCase {
    private final List<FoodInfo> foodsList;

    public GetFoodsOrderedUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public List<FoodInfo> execute(String sortBy) {
        Comparator<FoodInfo> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(FoodInfo::name).thenComparing(FoodInfo::quantity);

            case "quantity" -> Comparator.comparing(FoodInfo::quantity).thenComparing(FoodInfo::price);

            case "price" -> Comparator.comparing(FoodInfo::price).thenComparing(FoodInfo::calories);

            case "calories" -> Comparator.comparing(FoodInfo::calories).thenComparing(FoodInfo::id);

            case "id" -> Comparator.comparing(FoodInfo::id);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return foodsList.stream()
                .sorted(comparator)
                .toList();
    }
}
