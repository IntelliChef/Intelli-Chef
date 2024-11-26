package com.intelliChef.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void testRecipeInitializationWithValidData() {
        // Arrange
        Recipe recipe = new Recipe("Burger", "image_url", 500.0, 20, "recipe_url");

        // Assert
        assertEquals("Burger", recipe.getName());
        assertEquals("image_url", recipe.getImageUrl());
        assertEquals(500.0, recipe.getCalories());
        assertEquals(20, recipe.getCookingTime());
        assertEquals("recipe_url", recipe.getUrl());
    }

    @Test
    void testRecipeInitializationWithZeroCalories() {
        // Arrange
        Recipe recipe = new Recipe("Salad", "image_url", 0.0, 15, "recipe_url");

        // Assert
        assertEquals(0.0, recipe.getCalories());
    }

    @Test
    void testRecipeInitializationWithNegativeCookingTime() {
        // Arrange
        Recipe recipe = new Recipe("Ice Cream", "image_url", 300.0, -1, "recipe_url");

        // Assert
        assertEquals(-1, recipe.getCookingTime());
    }
}
