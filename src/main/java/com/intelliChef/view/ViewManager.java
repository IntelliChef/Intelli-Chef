package com.intelliChef.view;

import com.intelliChef.app.IngredientListFactory;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.frameworks.factories.RecipeViewFactory;
import com.intelliChef.adapters.presentation.RecipeListView;

/**
 * Builder that creates all the views and implements NavigationCall to allow controllers to access its methods.
 */
public class ViewManager implements NavigationCall {
    private final ViewFactory viewFactory;
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private IngredientRepository ingredientRepository;
    private final RecipeViewFactory recipeViewFactory;
    private RecipeListView recipeListView;

    public ViewManager(
            ViewFactory viewFactory,
            AnalyzeImageInteractor analyzeImageInteractor,
            RecipeViewFactory recipeViewFactory) {
        this.viewFactory = viewFactory;
        this.analyzeImageInteractor = analyzeImageInteractor;
        this.recipeViewFactory = recipeViewFactory;
    }

    public void showRecipeUploadView() {
        RecipeUploadView recipeUploadView = viewFactory.createRecipeUploadView(analyzeImageInteractor);
        recipeUploadView.setVisible(true);
    }

    public void showIngredientsDetectedView(IngredientRepository ingredientRepo) {
        IngredientsDetectedView ingredientsDetectedView = viewFactory.createIngredientsDetectedView(ingredientRepo);
        ingredientsDetectedView.setVisible(true);
    }

    public void showIngredientListView(IngredientRepository ingredientRepository) {
        IngredientListView ingredientListView = IngredientListFactory.create(ingredientRepository, this);
        ingredientListView.setVisible(true);
    }

    public void showRecipeListView() {
        recipeListView = recipeViewFactory.createRecipeListView();
        ((javax.swing.JFrame) recipeListView).setVisible(true);
    }

    @Override
    public void navigateToRecipeUploadView() {
        showRecipeUploadView();
    }

    @Override
    public void navigateToIngredientsDetectedView(IngredientRepository ingredientRepo) {
        showIngredientsDetectedView(ingredientRepo);
    }

    @Override
    public void navigateToIngredientListView(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        showIngredientListView(ingredientRepository);
    }

    @Override
    public void navigateToDietPreferenceView() {
        // To be implemented by Harpuneet by using ingredientRepository
    }
}