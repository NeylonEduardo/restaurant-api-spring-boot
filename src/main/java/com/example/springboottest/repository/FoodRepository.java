package com.example.springboottest.repository;

import com.example.springboottest.domain.FoodId;
import com.example.springboottest.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, FoodId> {
}
