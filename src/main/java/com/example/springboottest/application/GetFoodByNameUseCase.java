package com.example.springboottest.application;

import com.example.springboottest.exception.RestaurantNotFoundException;
import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GetFoodByNameUseCase {
    private final List<FoodInfo> foodsList;

    public GetFoodByNameUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public FoodInfo execute(String name) {
        return foodsList.stream()
                .filter(food -> Objects.equals(food.name(), name))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException(name + " not found"));
    }
}
