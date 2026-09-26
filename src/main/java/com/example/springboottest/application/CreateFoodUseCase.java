package com.example.springboottest.application;

import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateFoodUseCase {
    private final List<FoodEntity> foodsList;

    public CreateFoodUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public String execute(FoodEntity entity) {
        foodsList.add(entity);
            return String.format("%d food added to the list!", foodsList.size());
    }
}