package com.intelliChef.view;

import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;


public class ViewManager implements NavigationCall {
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

    public void showIngredientsDetectedView(IngredientListRepository ingredientRepo) {
        IngredientsDetectedView ingredientsDetectedView = viewFactory.createIngredientsDetectedView(ingredientRepo);
        ingredientsDetectedView.setVisible(true);
    }

    @Override
    public void navigateToRecipeUploadView() {
        showRecipeUploadView();
    }

    @Override
    public void navigateToIngredientsDetectedView(IngredientListRepository ingredientRepo) {
        showIngredientsDetectedView(ingredientRepo);
    }
}
