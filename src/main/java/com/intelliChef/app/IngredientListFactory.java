package com.intelliChef.app;

import com.intelliChef.adapters.ingredient_list.AddIngredientController;
import com.intelliChef.adapters.ingredient_list.GetIngredientListPresenter;
import com.intelliChef.adapters.ingredient_list.IngredientListViewModel;
import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.get_ingredient_list.GetIngredientListInteractor;
import com.intelliChef.view.IngredientListView;

/**
 * This class contains the static factory function for creating the IngredientListView.
 */
public final class IngredientListFactory {

    /**
     * Prevent instantiation.
     */
    private IngredientListFactory() {
    }

    /**
     * Factory function for creating the IngredientListView.
     */
    public static IngredientListView create(IngredientRepository ingredientRepository) {
        // implement later
    }
}

