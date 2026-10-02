package com.neylon.restaurantapi.exception;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidListEmpty {
    public void validNotEmptyList(List<?> list) {
        if (list.isEmpty()) {
            throw new FoodNotFoundException("This list is empty!");
        }
    }
}
