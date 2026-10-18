package com.neylon.restaurantapi.model;

import com.neylon.restaurantapi.domain.CategoryId;
import com.neylon.restaurantapi.enums.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @EmbeddedId
    private CategoryId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryType name;
}
