package com.intelliChef.data_access;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

import java.util.List;

public class IngredientListRepository implements IngredientRepository {
    private List<Ingredient> myIngredients;

    @Override
    public List<Ingredient> getAllIngredients() {
        return myIngredients;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {

    }
    // to be implemented later.
}
