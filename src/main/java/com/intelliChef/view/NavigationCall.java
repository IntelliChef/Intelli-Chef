package com.intelliChef.view;

import com.intelliChef.data_access.IngredientListRepository;

/**
 * Interface for navigation callbacks to be used by controllers, presenters and/or view models.
 */
public interface NavigationCall {
    /**
     * Loads the recipe upload view.
     */
    void navigateToRecipeUploadView();

    /**
     * Loads the ingredients detection view.
     * @param ingredientRepository repository interface which contains the new ingredients detected after scanning image
     */
    void navigateToIngredientsDetectedView(IngredientListRepository ingredientRepository);
}
