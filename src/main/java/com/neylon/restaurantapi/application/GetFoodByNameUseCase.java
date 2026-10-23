package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.FoodNotFoundException;
import com.neylon.restaurantapi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class GetFoodByNameUseCase {
    private final FoodRepository repository;

    public GetFoodByNameUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public FoodOutput execute(String name) {
        FoodOutput saved = repository.findByNameIgnoreCase(name)
                .map(FoodOutput::from)
                .orElseThrow(
                        () -> new FoodNotFoundException("This food does not exist")
                );

        log.info("Food with name founded. name={}", name);

        return saved;
    }
}