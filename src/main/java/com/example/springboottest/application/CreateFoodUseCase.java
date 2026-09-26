package com.example.springboottest.application;

import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateFoodUseCase {
    private final List<FoodInfo> foodsList;

    public CreateFoodUseCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public String execute(List<FoodInfo> foodList) {
        foodsList.addAll(foodList);
        return String.format("%d foods added to the list!", foodsList.size());
    }
}
