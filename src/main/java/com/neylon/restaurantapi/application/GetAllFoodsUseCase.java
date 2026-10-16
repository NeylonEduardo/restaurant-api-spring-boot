package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.ValidListEmpty;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetAllFoodsUseCase {
    private final FoodRepository repository;
    private final ValidListEmpty validListEmpty;

    public GetAllFoodsUseCase(FoodRepository repository, ValidListEmpty validListEmpty) {
        this.repository = repository;
        this.validListEmpty = validListEmpty;
    }

    @Transactional(readOnly = true)
    public List<FoodOutput> execute() {
        List<FoodEntity> foodsList = repository.findAll();

        validListEmpty.validNotEmptyList(foodsList);

        return foodsList.stream()
                .map(FoodOutput::from)
                .toList();

    }
}
