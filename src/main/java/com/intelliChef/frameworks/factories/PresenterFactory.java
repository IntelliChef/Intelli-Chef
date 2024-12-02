package com.intelliChef.frameworks.factories;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedPresenter;
import com.intelliChef.adapters.recipeUpload.RecipeUploadPresenter;
import com.intelliChef.view.IngredientsDetectedView;
import com.intelliChef.view.RecipeUploadView;

public class PresenterFactory {
    public RecipeUploadPresenter getRecipeUploadPresenter(RecipeUploadView recipeUploadView) {
        return new RecipeUploadPresenter(recipeUploadView);
    }

    public IngredientsDetectedPresenter getIngredientsDetectedPresenter(IngredientsDetectedView ingredientsDetectedView) {
        return new IngredientsDetectedPresenter(ingredientsDetectedView);
    }
}
