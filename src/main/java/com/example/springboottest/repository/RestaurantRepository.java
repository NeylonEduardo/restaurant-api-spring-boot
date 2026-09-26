package com.example.springboottest.repository;

import com.example.springboottest.model.FoodEntity;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class RestaurantRepository {
    private final List<FoodEntity> foodsList = new ArrayList<>();
}