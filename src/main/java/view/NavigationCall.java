package view;


import use_case.IngredientRepository;


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
    void navigateToIngredientsDetectedView(IngredientRepository ingredientRepository);

    /**
     * Loads the ingredient list view.
     * @param ingredientRepository which contains the Ingredient repository.
     */
    void navigateToIngredientListView(IngredientRepository ingredientRepository);

    /**
     * Loads the diet preference view.
     */
    void navigateToDietPreferenceView();

    /**
     * Loads the recipe list view
     */
    void navigateToRecipeView();
}
