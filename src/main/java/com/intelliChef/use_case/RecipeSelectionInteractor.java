package com.intelliChef.use_case;

import com.intelliChef.entities.Recipe;

/**
 * Handles the selection of a recipe and triggers the display of its details.
 */
public class RecipeSelectionInteractor {

    /**
     * Defines the output boundary for recipe selection.
     * Classes implementing this interface should handle the logic for displaying recipe details.
     */
    public interface OutputBoundary {
        /**
         * Displays the details of the selected recipe.
         *
         * @param recipe the {@link Recipe} instance containing the details to be displayed
         */
        void showRecipeDetails(Recipe recipe);
    }

    private final OutputBoundary outputBoundary;

    /**
     * Constructs a new RecipeSelectionInteractor with the specified output boundary.
     *
     * @param outputBoundary the {@link OutputBoundary} instance responsible for handling the display logic
     */
    public RecipeSelectionInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Handles the selection of a recipe and delegates the task of displaying its details
     * to the output boundary.
     *
     * @param recipe the {@link Recipe} instance that was selected
     */
    public void selectRecipe(Recipe recipe) {
        outputBoundary.showRecipeDetails(recipe);
    }
}
