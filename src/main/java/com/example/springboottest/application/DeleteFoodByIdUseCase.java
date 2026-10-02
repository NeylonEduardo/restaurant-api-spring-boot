package com.example.springboottest.application;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.exception.FoodNotFoundException;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteFoodByIdUseCase {
    private final FoodRepository repository;

    public DeleteFoodByIdUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public String execute(FoodId id) {
        FoodEntity removedFood = repository.findById(id)
                .orElseThrow(
                        () -> new FoodNotFoundException("Food not found")
                );
        repository.delete(removedFood);
        return String.format("%s removed from the list", removedFood.getName());
    }
}
