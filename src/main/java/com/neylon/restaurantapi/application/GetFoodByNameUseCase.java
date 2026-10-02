package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.FoodNotFoundException;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFoodByNameUseCase {
    private final FoodRepository repository;

    public GetFoodByNameUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public List<FoodOutput> execute(String name) {
        List<FoodOutput> foodName = repository.findByNameIgnoreCase(name)
                .stream()
                .map(FoodOutput::from)
                .toList();

        if (foodName.isEmpty()) throw new FoodNotFoundException(String.format("%s does not exist", name));

        return foodName;
    }
}
