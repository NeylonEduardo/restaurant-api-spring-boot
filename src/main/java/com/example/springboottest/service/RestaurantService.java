package com.example.springboottest.service;

import com.example.springboottest.model.FoodInfo;
import com.example.springboottest.exception.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {
    private final List<FoodInfo> foodsList = new ArrayList<>();


    public String createFood(List<FoodInfo> foodList) {
        foodsList.addAll(foodList);
        return String.format("%d foods added to the list!", foodsList.size());
    }

    public List<FoodInfo> getAllFoods() {
        validNotEmptyList(foodsList);
        return foodsList;
    }

    public List<FoodInfo> getFoodsOrderedBy(String sortBy) {
        Comparator<FoodInfo> comparator = switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(FoodInfo::name).thenComparing(FoodInfo::quantity);

            case "quantity" -> Comparator.comparing(FoodInfo::quantity).thenComparing(FoodInfo::price);

            case "price" -> Comparator.comparing(FoodInfo::price).thenComparing(FoodInfo::calories);

            case "calories" -> Comparator.comparing(FoodInfo::calories).thenComparing(FoodInfo::id);

            case "id" -> Comparator.comparing(FoodInfo::id);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return foodsList.stream()
                .sorted(comparator)
                .toList();
    }

    public FoodInfo getFoodById(Long id) {
        return foodsList.stream()
                .filter(food -> Objects.equals(food.id(), id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Id does not exist"));
    }

    public FoodInfo search(String name) {
        return foodsList.stream()
                .filter(food -> Objects.equals(food.name(), name))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException(name + " not found"));
    }

    public List<String> summary() {
        return foodsList.stream()
                .map(FoodInfo::name)
                .toList();
    }

    public List<FoodInfo> maxPriceFoods(BigDecimal maxPrice) {
        validNotEmptyList(foodsList);
        return foodsList.stream()
                .filter(food -> food.price().compareTo(maxPrice) <= 0)
                .toList();
    }

    public String deleteFoodById(Long id) {
        FoodInfo foodToRemove = foodsList.stream()
                .filter(food -> Objects.equals(food.id(), id))
                .findFirst()
                .orElseThrow(() ->
                        new RestaurantNotFoundException(id + " not find"));

        foodsList.remove(foodToRemove);
        return String.format("%s removed from the list", foodToRemove.name());
    }

    private void validNotEmptyList(List<?> list) {
        if (list.isEmpty()) {
            throw new RestaurantNotFoundException("This list is empty!");
        }
    }
}