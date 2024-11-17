package com.intelliChef.use_case;

import com.intelliChef.entities.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> getAllIngredients();
    void addIngredient(Ingredient ingredient);
}
