package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.domain.FoodId;
import com.neylon.restaurantapi.exception.FoodNotFoundException;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("Food deleted successfully. id={}", id.id());

        return String.format("%s removed from the list", removedFood.getName());
    }
}
