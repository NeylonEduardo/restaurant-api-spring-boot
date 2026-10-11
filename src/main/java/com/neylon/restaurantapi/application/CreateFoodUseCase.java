package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.dto.FoodRequest;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        FoodEntity saved = repository.save(entity);

        log.info("Food created successfully. id={}", saved.getId().id());

        return FoodOutput.from(saved);
    }
}