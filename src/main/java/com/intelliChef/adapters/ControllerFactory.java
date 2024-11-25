package com.intelliChef.adapters;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.recipeUpload.RecipeUploadController;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.NavigationCall;

public class ControllerFactory {
    public RecipeUploadController getRecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor, NavigationCall navigationCall) {
        return new RecipeUploadController(analyzeImageInteractor, navigationCall);
    }

    public IngredientsDetectedController getIngredientsDetectedController(NavigationCall navigationCall) {
        return new IngredientsDetectedController(navigationCall);
    }
}
