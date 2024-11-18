package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.entities.Ingredient;

import java.util.List;

public class GetIngredientListOutputData {
    private final List<Ingredient> ingredients;

    public GetIngredientListOutputData(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
