package com.intelliChef.view;


import com.intelliChef.use_case.IngredientListRepositoryInterface;


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
     * @param ingredientRepository repository implementation that contains the ingredients detected after scanning image.
     */
    void navigateToIngredientsDetectedView(IngredientListRepositoryInterface ingredientRepository);

    /**
     * Loads the ingredient list view.
     * @param ingredientRepository which contains the Ingredient repository.
     */
    void navigateToIngredientListView(IngredientListRepositoryInterface ingredientRepository);

    /**
     * Loads the diet preference view.
     */
    void navigateToDietPreferenceView();
}
