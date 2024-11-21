package com.intelliChef.presenters;

import com.intelliChef.entities.Recipe;
import com.intelliChef.use_case.RecipeSelectionInteractor.OutputBoundary;

public class RecipePresenter implements OutputBoundary {
    private final RecipeView view;

    public RecipePresenter(RecipeView view) {
        this.view = view;
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        view.displayRecipe(recipe);
    }
}
