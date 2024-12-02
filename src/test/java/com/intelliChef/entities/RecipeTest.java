package com.intelliChef.entities;

import entities.Recipe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    @Test
    void testRecipeCreation() {
        Recipe recipe = new Recipe("Test Recipe", "http://image.url", 500.0, 30, "http://recipe.url");

        assertEquals("Test Recipe", recipe.getName());
        assertEquals("http://image.url", recipe.getImageUrl());
        assertEquals(500.0, recipe.getCalories());
        assertEquals(30, recipe.getCookingTime());
        assertEquals("http://recipe.url", recipe.getUrl());
    }

    @Test
    void testRecipeImmutability() {
        Recipe recipe = new Recipe("Test Recipe", "http://image.url", 500.0, 30, "http://recipe.url");

        assertNotNull(recipe.getName());
        assertNotNull(recipe.getImageUrl());
        assertNotNull(recipe.getUrl());
    }
}