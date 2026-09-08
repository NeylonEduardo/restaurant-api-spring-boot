package com.example.springboottest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping("/foods")
    public ResponseEntity<String> postFood(@RequestBody List<@Valid FoodInfo> foodInfoList) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createFood(foodInfoList));
    }

    @GetMapping("/foods")
    public ResponseEntity<List<FoodInfo>> getAllFoods() {
        return ResponseEntity.ok(service.getAllFoods());
    }

    @GetMapping("/foods/order")
    public ResponseEntity<List<FoodInfo>> sortBy(@RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(service.getFoodsOrderedBy(sortBy));
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<FoodInfo> getFood(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.getFoodById(id));
    }

    @GetMapping("/foods/search")
    public ResponseEntity<FoodInfo> searchFood(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(service.search(name));
    }

    @GetMapping("/foods/summary")
    public ResponseEntity<List<String>> getFoodsSummary() {
        return ResponseEntity.ok(service.summary());
    }

    @GetMapping("/foods/filter")
    public ResponseEntity<List<FoodInfo>> findByMaxPrice(@RequestParam @Positive BigDecimal maxPrice) {
        return ResponseEntity.ok(service.maxPriceFoods(maxPrice));
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteFoodById(id));
    }
}
