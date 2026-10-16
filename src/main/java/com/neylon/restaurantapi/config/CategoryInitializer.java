package com.neylon.restaurantapi.config;

import com.neylon.restaurantapi.enums.CategoryType;
import com.neylon.restaurantapi.model.CategoryEntity;
import com.neylon.restaurantapi.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryInitializer {

    @Bean
    public CommandLineRunner initializeCategories(
            CategoryRepository repository
    ) {
        return args -> {

            for (CategoryType type : CategoryType.values()) {
                if (!repository.existsByName(type)) {

                    CategoryEntity category = new CategoryEntity();
                    category.setName(type);

                    repository.save(category);
                }
            }
        };
    }
}