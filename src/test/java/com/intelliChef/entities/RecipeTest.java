package com.intelliChef.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void testRecipeConstructorAndGetters() {
        Recipe recipe = new Recipe(
                "Spaghetti Bolognese",
                "https://example.com/image.jpg",
                500.5,
                30,
                "https://example.com/recipe"
        );

        assertEquals("Spaghetti Bolognese", recipe.getName());
        assertEquals("https://example.com/image.jpg", recipe.getImageUrl());
        assertEquals(500.5, recipe.getCalories());
        assertEquals(30, recipe.getCookingTime());
        assertEquals("https://example.com/recipe", recipe.getUrl());
    }

    @Test
    void testRecipeWithNoCookingTime() {
        Recipe recipe = new Recipe(
                "Fruit Salad",
                "https://example.com/salad.jpg",
                150.0,
                0, // No cooking time
                "https://example.com/salad"
        );

        assertEquals(0, recipe.getCookingTime());
        assertEquals("Fruit Salad", recipe.getName());
    }
}
