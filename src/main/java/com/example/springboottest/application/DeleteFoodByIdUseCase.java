package com.example.springboottest.application;

import com.example.springboottest.exception.RestaurantNotFoundException;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeleteFoodByIdUseCase {
    private final List<FoodEntity> foodsList;

    public DeleteFoodByIdUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public String execute(Long id) {
        FoodEntity foodToRemove = foodsList.stream()
                .filter(food -> Objects.equals(food.id(), id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException(id + " not find"));

        foodsList.remove(foodToRemove);
        return String.format("%s removed from the list", foodToRemove.name());
    }
}
