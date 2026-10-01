package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFoodByNameUseCase {
    private final FoodRepository repository;

    public GetFoodByNameUseCase(FoodRepository repository) {
        this.repository = repository;
    }

    public List<FoodOutput> execute(String name) {
        return repository.findByNameIgnoreCase(name)
                .stream()
                .map(FoodOutput::from)
                .toList();
    }
}
