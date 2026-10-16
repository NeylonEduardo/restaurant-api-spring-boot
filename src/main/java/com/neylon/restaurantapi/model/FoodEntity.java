package com.neylon.restaurantapi.model;

import com.neylon.restaurantapi.domain.FoodId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "food")
public class FoodEntity {

    @EmbeddedId
    private FoodId id;

    private String name;
    private BigDecimal price;
    private float calories;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public FoodEntity(String name, BigDecimal price, float calories, int quantity, CategoryEntity category) {

        Assert.notNull(name, "Name must not be null");
        Assert.notNull(category, "Category must not be null");

        this.id = new FoodId();
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.quantity = quantity;
        this.category = category;
    }
}