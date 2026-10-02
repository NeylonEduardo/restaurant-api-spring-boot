package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.domain.FoodId;
import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.FoodNotFoundException;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class GetFoodByIdUseCase {
    private final FoodRepository repository;

    public GetFoodByIdUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public FoodOutput execute(FoodId id) {
        return repository.findById(id)
                .map(FoodOutput::from)
                .orElseThrow(
                        () -> new FoodNotFoundException("id does not exist")
                );
    }
}
