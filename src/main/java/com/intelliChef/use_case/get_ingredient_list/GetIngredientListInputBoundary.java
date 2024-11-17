package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.entities.Ingredient;

import java.util.List;

public interface GetIngredientListInputBoundary {
    void execute(GetIngredientListInputData inputData);
}
