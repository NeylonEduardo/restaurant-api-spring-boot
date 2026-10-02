package com.neylon.restaurantapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record FoodRequest(

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotNull(message = "Price is mandatory")
        @DecimalMin(value = "0.01", message = "Price must be greater than zero")
        BigDecimal price,

        @Positive(message = "Calories must be greater than zero")
        float calories,

        @PositiveOrZero(message = "Quantity cannot be negative")
        int quantity
) {
}
