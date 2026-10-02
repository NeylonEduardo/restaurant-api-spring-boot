package com.neylon.restaurantapi.application;

import com.neylon.restaurantapi.dto.FoodOutput;
import com.neylon.restaurantapi.exception.ValidListEmpty;
import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GetMaxPriceFoodsUseCase {
    private final FoodRepository repository;
    private final ValidListEmpty validListEmpty;

    public GetMaxPriceFoodsUseCase(FoodRepository repository, ValidListEmpty validListEmpty) {
        this.repository = repository;
        this.validListEmpty = validListEmpty;
    }

    public List<FoodOutput> execute(BigDecimal maxPrice) {
        List<FoodEntity> foods = repository.findByPriceLessThanEqual(maxPrice);

        validListEmpty.validNotEmptyList(foods);

        return foods.stream()
                .map(FoodOutput::from)
                .toList();
    }
}
