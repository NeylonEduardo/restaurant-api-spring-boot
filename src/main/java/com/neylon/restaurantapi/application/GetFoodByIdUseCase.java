package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.domain.FoodId;
import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.FoodNotFoundException;
import com.neylon.restaurantapi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class GetFoodByIdUseCase {
    private final FoodRepository repository;

    public GetFoodByIdUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public FoodOutput execute(FoodId id) {
        FoodOutput saved = repository.findById(id)
                .map(FoodOutput::from)
                .orElseThrow(
                        () -> new FoodNotFoundException("id does not exist")
                );

        log.info("Food with id founded. id={}", id);

        return saved;
    }
}
