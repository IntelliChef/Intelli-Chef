package com.intelliChef.use_case.get_ingredient_list;

import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;

public interface GetIngredientListOutputBoundary {
    void present(GetIngredientListOutputData outputData);
    IngredientListViewModel getViewModel();
}
