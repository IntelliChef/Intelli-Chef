package com.intelliChef.use_case;

import com.intelliChef.entities.Recipe;

public class RecipeSelectionInteractor {
    public interface OutputBoundary {
        void showRecipeDetails(Recipe recipe);
    }

    private final OutputBoundary outputBoundary;

    public RecipeSelectionInteractor(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void selectRecipe(Recipe recipe) {
        outputBoundary.showRecipeDetails(recipe);
    }
}
