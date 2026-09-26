package com.example.springboottest.application;

import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.exception.ValidListEmpty;
import com.example.springboottest.model.FoodEntity;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GetMaxPriceFoodsUseCase {
    private final List<FoodEntity> foodsList;
    private final ValidListEmpty validListEmpty;

    public GetMaxPriceFoodsUseCase(RestaurantRepository restaurantRepository, ValidListEmpty validListEmpty) {
        this.foodsList = restaurantRepository.getFoodsList();
        this.validListEmpty = validListEmpty;
    }

    public List<FoodOutput> execute(BigDecimal maxPrice) {
        validListEmpty.validNotEmptyList(foodsList);
        return foodsList.stream()
                .filter(food -> food.getPrice().compareTo(maxPrice) <= 0)
                .map(FoodOutput::from)
                .toList();
    }
}
