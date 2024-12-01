package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;

/**
 * The output boundary for the Get Ingredient List Use Case.
 */
public interface GetIngredientListOutputBoundary {
    /**
     * Creates an Ingredient List View Model based on the given output data
     * @param outputData a list of ingredients
     */
    void present(GetIngredientListOutputData outputData);
    IngredientListViewModel getViewModel();
}
