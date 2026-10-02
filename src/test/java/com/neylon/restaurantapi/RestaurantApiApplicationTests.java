package com.neylon.restaurantapi;

import com.neylon.restaurantapi.model.FoodEntity;
import com.neylon.restaurantapi.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RestaurantApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateFood() throws Exception {
        String requestBody = """
                {
                  "name": "Margherita Pizza",
                  "price": 32.50,
                  "calories": 720.0,
                  "quantity": 8
                }
                """;

        mockMvc.perform(post("/restaurant/foods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Margherita Pizza"))
                .andExpect(jsonPath("$.price").value(32.50))
                .andExpect(jsonPath("$.calories").value(720.0))
                .andExpect(jsonPath("$.quantity").value(8));

        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    void shouldFindFoodById() throws Exception {
        FoodEntity savedFood = repository.save(
                new FoodEntity(
                        "Cheeseburger",
                        new BigDecimal("25.90"),
                        650.0f,
                        10
                )
        );

        UUID id = savedFood.getId().id();

        mockMvc.perform(get("/restaurant/foods/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Cheeseburger"))
                .andExpect(jsonPath("$.price").value(25.90))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void shouldReturnNotFoundWhenFoodDoesNotExist() throws Exception {
        UUID nonexistentId = UUID.randomUUID();

        mockMvc.perform(get("/restaurant/foods/{id}", nonexistentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("id does not exist"));
    }

    @Test
    void shouldDeleteFoodById() throws Exception {
        FoodEntity savedFood = repository.save(
                new FoodEntity(
                        "French Fries",
                        new BigDecimal("15.00"),
                        350.0f,
                        20
                )
        );

        UUID id = savedFood.getId().id();

        mockMvc.perform(delete("/restaurant/foods/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("French Fries removed from the list"));

        assertThat(repository.existsById(savedFood.getId())).isFalse();
    }

    @Test
    void shouldRejectInvalidFood() throws Exception {
        String invalidRequest = """
                {
                  "name": "",
                  "price": 0,
                  "calories": -10,
                  "quantity": -1
                }
                """;

        mockMvc.perform(post("/restaurant/foods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest());

        assertThat(repository.count()).isZero();
    }
}