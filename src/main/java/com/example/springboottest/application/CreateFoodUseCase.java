package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.dto.FoodRequest;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
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