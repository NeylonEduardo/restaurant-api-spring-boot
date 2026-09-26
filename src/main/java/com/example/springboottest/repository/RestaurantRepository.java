package com.example.springboottest.repository;

import com.example.springboottest.model.FoodEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepository {
    private final List<FoodEntity> foodsList = new ArrayList<>();

    public List<FoodEntity> getFoodsList() {
        return this.foodsList;
    }
}
