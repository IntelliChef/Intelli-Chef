package com.intelliChef.view;

import com.intelliChef.entities.Ingredient;

import java.util.List;

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
     * @param ingredientList list which contains the new ingredients detected after scanning image
     */
    void navigateToIngredientsDetectedView(List<Ingredient> ingredientList);

    void navigateToIngredientListView();
}
