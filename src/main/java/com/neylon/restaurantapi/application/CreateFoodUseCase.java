package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.dto.FoodRequest;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateFoodUseCase {
    private final FoodRepository repository;

    public CreateFoodUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public FoodOutput execute(FoodRequest request) {
        FoodEntity entity = new FoodEntity(
                request.name(),
                request.price(),
                request.calories(),
                request.quantity());

        repository.save(entity);

        return FoodOutput.from(entity);
    }
}