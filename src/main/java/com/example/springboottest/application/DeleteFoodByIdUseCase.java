package com.example.springboottest.application;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.exception.FoodNotFoundException;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeleteFoodByIdUseCase {
    private final FoodRepository repository;

    public DeleteFoodByIdUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public String execute(FoodId id) {
        FoodEntity foodToRemove = repository.findAll()
                .stream()
                .filter(food -> Objects.equals(food.getId(), id))
                .findFirst()
                .orElseThrow(() ->
                        new FoodNotFoundException(id + " not find"));

        repository.delete(foodToRemove);
        return String.format("%s removed from the list", foodToRemove.getName());
    }
}
