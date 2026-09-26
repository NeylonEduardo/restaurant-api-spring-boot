package com.example.springboottest.model;

import com.example.springboottest.domain.FoodId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Getter
@Setter
public class FoodEntity {
    private FoodId id;
    private String name;
    private BigDecimal price;
    private float calories;
    private int quantity;

    public FoodEntity(String name, BigDecimal price, float calories, int quantity) {
        Assert.notNull(name, "Name must not be null");

        this.id = new FoodId();
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
    }
}
