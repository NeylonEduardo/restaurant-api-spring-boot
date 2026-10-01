package com.example.springboottest.application;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.exception.FoodNotFoundException;
import com.example.springboottest.repository.FoodRepository;
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
