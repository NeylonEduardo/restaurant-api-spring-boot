package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetAllFoodsUseCase {
    private final FoodRepository repository;

    public GetAllFoodsUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<FoodOutput> execute(Pageable pageable) {
        return repository.findAll(pageable)
                .map(FoodOutput::from);
    }
}