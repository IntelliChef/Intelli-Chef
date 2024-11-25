package com.intelliChef.data_access;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

import java.util.ArrayList;
import java.util.List;

public class IngredientListRepository implements IngredientRepository {
    private final List<Ingredient> myIngredients = new ArrayList<Ingredient>();

    @Override
    public List<Ingredient> getAllIngredients() {
        return myIngredients;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        myIngredients.add(ingredient);
    }

    @Override
    public void deleteIngredient(int id) {
        for (Ingredient ingredient : myIngredients) {
            if (ingredient.getId() == (id)) {
                myIngredients.remove(ingredient);
                break;
            }
        }
        System.out.println("Ingredient ID doesn't exist");
    }

    @Override
    public int getNextId() {
        return myIngredients.size() + 1; // Might have to change this
    }
}
