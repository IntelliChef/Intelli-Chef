package com.intelliChef.view;


import com.intelliChef.data_access.IngredientListRepository;
import com.intelliChef.app.IngredientListFactory;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;


public class ViewManager implements NavigationCall {
    private final ViewFactory viewFactory;
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private IngredientRepository ingredientRepository;


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

    public void showIngredientListView(IngredientRepository ingredientRepository) {
        IngredientListView ingredientListView = IngredientListFactory.create(ingredientRepository, this);
        ingredientListView.setVisible(true);
    }

    @Override
    public void navigateToRecipeUploadView() {
        showRecipeUploadView();
    }

    @Override
    public void navigateToIngredientsDetectedView(IngredientListRepository ingredientRepo) {
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
