package com.intelliChef.presenters;

import com.intelliChef.entities.Recipe;

/**
 * Represents the view interface for displaying the details of a recipe.
 * Classes implementing this interface are responsible for rendering
 * the recipe details to the user.
 */
public interface RecipeView {

    /**
     * Displays the details of the given recipe.
     *
     * @param recipe the {@link Recipe} instance containing the details to display
     */
    void displayRecipe(Recipe recipe);
}
