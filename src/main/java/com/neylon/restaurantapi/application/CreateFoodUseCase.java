package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.dto.FoodRequest;
import com.neylon.restaurantapi.exception.CategoryNotFoundException;
import com.neylon.restaurantapi.model.CategoryEntity;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.CategoryRepository;
import com.neylon.restaurantapi.repository.FoodRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateFoodUseCase {
    private final FoodRepository repository;
    private final CategoryRepository categoryRepository;

    public CreateFoodUseCase(FoodRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public FoodOutput execute(FoodRequest request) {
        CategoryEntity category = categoryRepository
                .findByName(request.categoryName())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        FoodEntity entity = new FoodEntity(
                request.name(),
                request.price(),
                request.calories(),
                request.quantity(),
                category);

        FoodEntity saved = repository.save(entity);

        log.info("Food created successfully. id={}", saved.getId().id());

        return FoodOutput.from(saved);
    }
}