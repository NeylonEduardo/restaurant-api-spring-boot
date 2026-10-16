package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.enums.CategoryType;
import com.neylon.restaurantapi.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class GetFoodByCategoryUseCase {
    private final FoodRepository repository;

    public GetFoodByCategoryUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FoodOutput> execute(CategoryType category) {
        List<FoodOutput> saved = repository
                .findByCategoryName(category)
                .stream()
                .map(FoodOutput::from)
                .toList();

        log.info("Foods with specify category founded. category={}", category.toString());

        return saved;
    }
}
