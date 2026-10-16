package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetSummaryUserCase {
    private final FoodRepository repository;

    public GetSummaryUserCase(FoodRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<String> execute() {
        return repository.findAll()
                .stream()
                .map(FoodEntity::getName)
                .sorted()
                .toList();

    }
}
