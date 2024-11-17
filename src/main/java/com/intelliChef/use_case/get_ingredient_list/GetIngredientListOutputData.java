package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.entities.Ingredient;

import java.util.List;

public class GetIngredientListOutputData {
    private final List<Ingredient> ingredientList;

    public GetIngredientListOutputData(List<Ingredient> ingredients) {
        this.ingredientList = ingredients;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
}
