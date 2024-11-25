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

    /**
     * Delete ingredient with the id
     *
     * @param id the id of the ingredient to be deleted.
     */
    @Override
    public void deleteIngredient(int id) {

    }

    /**
     * Return the next possible id
     *
     * @return
     */
    @Override
    public int getNextId() {

    }

    // to be implemented later.
}
