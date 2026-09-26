package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.dto.FoodRequest;
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

    public FoodOutput execute(FoodRequest request) {
        FoodEntity entity = new FoodEntity(
                request.name(),
                request.price(),
                request.calories(),
                request.quantity());

        foodsList.add(entity);

        return FoodOutput.from(entity);
    }
}