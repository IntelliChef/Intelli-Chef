package com.intelliChef.adapters.recipeUpload;

import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.view.RecipeUploadView;

public class RecipeUploadFactory {
    public RecipeUploadController getController(AnalyzeImageInteractor analyzeImageInteractor) {
        return new RecipeUploadController(analyzeImageInteractor);
    }

    public RecipeUploadPresenter getPresenter(RecipeUploadView recipeUploadView) {
        return new RecipeUploadPresenter(recipeUploadView);
    }
}
