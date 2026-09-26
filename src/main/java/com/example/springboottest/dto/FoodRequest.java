package com.example.springboottest.dto;

import java.math.BigDecimal;

public record FoodRequest(String name, BigDecimal price, float calories, int quantity) {
}
