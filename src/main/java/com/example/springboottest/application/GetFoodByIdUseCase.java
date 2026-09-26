package com.example.springboottest.application;

import com.example.springboottest.exception.RestaurantNotFoundException;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GetFoodByIdUseCase {
    private final List<FoodEntity> foodsList;

    public GetFoodByIdUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public FoodEntity execute(Long id) {
        return foodsList.stream()
                .filter(food -> Objects.equals(food.id(), id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Id does not exist"));
    }
}
