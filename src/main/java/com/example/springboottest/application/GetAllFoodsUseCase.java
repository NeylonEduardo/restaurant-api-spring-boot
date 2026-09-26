package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.exception.ValidListEmpty;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFoodsUseCase {
    private final List<FoodEntity> foodsList;
    private final ValidListEmpty validListEmpty;

    public GetAllFoodsUseCase(RestaurantRepository restaurantRepository, ValidListEmpty validListEmpty) {
        this.foodsList = restaurantRepository.getFoodsList();
        this.validListEmpty = validListEmpty;
    }

    public List<FoodOutput> execute() {
        validListEmpty.validNotEmptyList(foodsList);

        return foodsList.stream()
                .map(FoodOutput::from)
                .toList();

    }
}
