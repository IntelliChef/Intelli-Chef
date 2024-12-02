package com.intelliChef.use_case.analyzeImage;

import com.intelliChef.entities.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeImageOutputData {
    private final List<Ingredient> ingredientList = new ArrayList<>();

    public AnalyzeImageOutputData(List<Ingredient> ingredientList) {
        this.ingredientList.addAll(ingredientList);
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public boolean isEmpty() {
        return ingredientList.isEmpty();
    }
}
