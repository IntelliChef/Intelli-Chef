package com.intelliChef.view;

import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;

import java.util.List;

public class ViewManager {
    private final ViewFactory viewFactory;
    private final AnalyzeImageInteractor analyzeImageInteractor;

    public ViewManager(ViewFactory viewFactory, AnalyzeImageInteractor analyzeImageInteractor) {
        this.viewFactory = viewFactory;
        this.analyzeImageInteractor = analyzeImageInteractor;
    }

    public void showRecipeUploadView() {
        RecipeUploadView recipeUploadView = viewFactory.createRecipeUploadView(analyzeImageInteractor);
        recipeUploadView.setVisible(true);
    }

    public void showIngredientsDetectedView(List<Ingredient> ingredientList) {
        IngredientsDetectedView ingredientsDetectedView = viewFactory.createIngredientsDetectedView(ingredientList);
        ingredientsDetectedView.setVisible(true);
    }
}
