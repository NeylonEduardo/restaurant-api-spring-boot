package com.example.springboottest.application;

import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSummaryUserCase {
    private final FoodRepository repository;

    public GetSummaryUserCase(FoodRepository repository) {
        this.repository = repository;
    }

    public List<String> execute() {
        return repository.findAll()
                .stream()
                .map(FoodEntity::getName)
                .toList();

    }
}
