package com.neylon.restaurantapi.repository;

import com.neylon.restaurantapi.enums.CategoryType;
import com.neylon.restaurantapi.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByName(CategoryType name);

    Optional<CategoryEntity> findByName(CategoryType name);
}
