package com.example.springboottest.application;

import com.example.springboottest.exception.ValidListEmpty;
import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllFoodsUseCase {
    private final List<FoodInfo> foodsList;
    private final ValidListEmpty validListEmpty;

    public GetAllFoodsUseCase(RestaurantRepository restaurantRepository, ValidListEmpty validListEmpty1) {
        this.foodsList = restaurantRepository.getFoodsList();
        this.validListEmpty = validListEmpty1;
    }

    public List<FoodInfo> execute() {
        validListEmpty.validNotEmptyList(foodsList);
        return foodsList;
    }
}
