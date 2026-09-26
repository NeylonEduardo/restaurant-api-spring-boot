package com.example.springboottest.application;

import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSummaryUserCase {
    private final List<FoodInfo> foodsList;

    public GetSummaryUserCase(RestaurantRepository restaurantRepository) {
        this.foodsList = restaurantRepository.getFoodsList();
    }

    public List<String> execute() {
        return foodsList.stream()
                .map(FoodInfo::name)
                .toList();
    }
}
