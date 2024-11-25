package com.intelliChef.adapters;

import com.intelliChef.adapters.ingredientsDetected.IngredientsDetectedController;
import com.intelliChef.adapters.recipeUpload.RecipeUploadController;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

public class ControllerFactory {
    public RecipeUploadController getRecipeUploadController(AnalyzeImageInteractor analyzeImageInteractor) {
        return new RecipeUploadController(analyzeImageInteractor);
    }

    public IngredientsDetectedController getIngredientsDetectedController() {
        return new IngredientsDetectedController();
    }
}
