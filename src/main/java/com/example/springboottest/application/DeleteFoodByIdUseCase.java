package com.example.springboottest.application;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.exception.RestaurantNotFoundException;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DeleteFoodByIdUseCase {
    private final FoodRepository repository;

    public DeleteFoodByIdUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public String execute(FoodId id) {
        List<FoodEntity> foodsList = repository.findAll();

        FoodEntity foodToRemove = foodsList.stream()
                .filter(food -> Objects.equals(food.getId(), id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException(id + " not find"));

        foodsList.remove(foodToRemove);
        return String.format("%s removed from the list", foodToRemove.getName());
    }
}
