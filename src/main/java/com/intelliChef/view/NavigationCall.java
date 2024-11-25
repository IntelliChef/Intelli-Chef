package com.intelliChef.view;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;

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

    /**
     * Loads the ingredient list view.
     * @param ingredientRepository which contains the Ingredient repository.
     */
    void navigateToIngredientListView(IngredientRepository ingredientRepository);

    /**
     * Loads the diet preference view.
     */
    void navigateToDietPreferenceView();
}
