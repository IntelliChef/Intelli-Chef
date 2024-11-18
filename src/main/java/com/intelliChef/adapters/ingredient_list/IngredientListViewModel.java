package com.intelliChef.adapters.ingredient_list;
import java.util.List;

public class IngredientListViewModel {
    private final List<String> ingredientsDisplayList; // UI-friendly format

    public IngredientListViewModel(List<String> ingredientsDisplayList) {
        this.ingredientsDisplayList = ingredientsDisplayList;
    }

    public List<String> getIngredientsDisplayList() {
        return ingredientsDisplayList;
    }
}

