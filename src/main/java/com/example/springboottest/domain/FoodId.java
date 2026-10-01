package com.example.springboottest.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.UUID;

@Embeddable
public record FoodId(UUID id) implements Comparable<FoodId> {
    public FoodId {
        Assert.notNull(id, "id must not be null");
    }

    public FoodId() {
        this(UUID.randomUUID());
    }

    @Override
    public int compareTo(FoodId other) {
        return this.id.compareTo(other.id);
    }
}
