package com.intelliChef.presenters;

import com.intelliChef.entities.Recipe;
import com.intelliChef.use_case.RecipeSelectionInteractor.OutputBoundary;

/**
 * The presenter responsible for handling the display of recipe details.
 * Acts as a bridge between the interactor and the recipe view.
 */
public class RecipePresenter implements OutputBoundary {
    private final RecipeView view;

    /**
     * Constructs a new RecipePresenter with the specified recipe view.
     *
     * @param view the {@link RecipeView} instance responsible for displaying recipe details
     */
    public RecipePresenter(RecipeView view) {
        this.view = view;
    }

    /**
     * Displays the details of a selected recipe.
     *
     * @param recipe the {@link Recipe} instance containing the recipe details to display
     */
    @Override
    public void showRecipeDetails(Recipe recipe) {
        view.displayRecipe(recipe);
    }
}
