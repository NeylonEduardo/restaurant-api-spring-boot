package com.example.springboottest.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FoodInfo(
        @NotBlank(message = "The name is mandatory")
        String name,

        @Min(value = 0, message = "Quantity must be greater than or equal to zero")
        int quantity,

        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.01", message = "Price need to be greater than zero")
        BigDecimal price,

        @NotNull(message = "Calories is mandatory")
        Float calories,

        @NotNull(message = "Id is mandatory")
        @Min(value = 1, message = "Id must be greater or equal to one")
        Long id
) {
}
