package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.exception.ValidListEmpty;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFoodsUseCase {
    private final FoodRepository repository;
    private final ValidListEmpty validListEmpty;

    public GetAllFoodsUseCase(FoodRepository repository, ValidListEmpty validListEmpty) {
        this.repository = repository;
        this.validListEmpty = validListEmpty;
    }

    public List<FoodOutput> execute() {
        List<FoodEntity> foodsList = repository.findAll();

        validListEmpty.validNotEmptyList(foodsList);

        return foodsList.stream()
                .map(FoodOutput::from)
                .toList();

    }
}
