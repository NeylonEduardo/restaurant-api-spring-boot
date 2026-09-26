package com.example.springboottest.application;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.dto.FoodOutput;
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

    public FoodOutput execute(FoodId id) {
        return foodsList.stream()
                .filter(food -> Objects.equals(food.getId(), id))
                .findFirst()
                .map(FoodOutput::from)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Id does not exist"));
    }
}
