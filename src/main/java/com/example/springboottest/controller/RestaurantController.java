package com.example.springboottest.controller;

import com.example.springboottest.application.*;
import com.example.springboottest.domain.FoodId;
import com.example.springboottest.dto.FoodOutput;
import com.example.springboottest.dto.FoodRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final CreateFoodUseCase createFood;
    private final DeleteFoodByIdUseCase deleteFoodById;
    private final GetAllFoodsUseCase getAllFoods;
    private final GetFoodByIdUseCase getFoodById;
    private final GetFoodByNameUseCase getFoodByName;
    private final GetMaxPriceFoodsUseCase getMaxPriceFoods;
    private final GetSummaryUserCase getSummary;
    private final GetFoodsOrderedUseCase getFoodsOrdered;

    public RestaurantController(CreateFoodUseCase createFood,
                                DeleteFoodByIdUseCase deleteFoodById,
                                GetAllFoodsUseCase getAllFoods,
                                GetFoodByIdUseCase getFoodById,
                                GetFoodByNameUseCase getFoodByName,
                                GetMaxPriceFoodsUseCase getMaxPriceFoods,
                                GetSummaryUserCase getSummary,
                                GetFoodsOrderedUseCase getFoodsOrdered) {

        this.createFood = createFood;
        this.deleteFoodById = deleteFoodById;
        this.getAllFoods = getAllFoods;
        this.getFoodById = getFoodById;
        this.getFoodByName = getFoodByName;
        this.getMaxPriceFoods = getMaxPriceFoods;
        this.getSummary = getSummary;
        this.getFoodsOrdered = getFoodsOrdered;
    }

    @PostMapping("/foods")
    public ResponseEntity<FoodOutput> postFoods(@RequestBody @Valid FoodRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createFood.execute(request));
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodOutput>> getAllFoods() {
        return ResponseEntity.ok(getAllFoods.execute());
    }

    @GetMapping("/foods/order")
    public ResponseEntity<List<FoodOutput>> sortBy(@RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(getFoodsOrdered.execute(sortBy));
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<FoodOutput> getFoodById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(getFoodById.execute(new FoodId(id)));
    }

    @GetMapping("/foods/search")
    public ResponseEntity<List<FoodOutput>> searchFood(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(getFoodByName.execute(name));
    }

    @GetMapping("/foods/summary")
    public ResponseEntity<List<String>> getFoodsSummary() {
        return ResponseEntity.ok(getSummary.execute());
    }

    @GetMapping("/foods/filter")
    public ResponseEntity<List<FoodOutput>> findByMaxPrice(@RequestParam @Positive BigDecimal maxPrice) {
        return ResponseEntity.ok(getMaxPriceFoods.execute(maxPrice));
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return ResponseEntity.ok(deleteFoodById.execute(new FoodId(id)));
    }
}
