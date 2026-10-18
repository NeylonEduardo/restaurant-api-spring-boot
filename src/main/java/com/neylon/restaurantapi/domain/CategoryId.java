package com.neylon.restaurantapi.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.UUID;

@Embeddable
public record CategoryId(UUID id) {
    public CategoryId {
        Assert.notNull(id, "Id must not be null");
    }

    public CategoryId() {
        this(UUID.randomUUID());
    }
}
