package com.example.springboottest.repository;

import com.example.springboottest.model.FoodInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<FoodInfo> foodsList = new ArrayList<>();

    public List<FoodInfo> getFoodsList() {
        return this.foodsList;
    }
}
