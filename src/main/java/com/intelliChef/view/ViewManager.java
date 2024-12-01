package com.intelliChef.view;

import com.intelliChef.app.IngredientListFactory;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.entities.Recipe;

/**
 * Builder that creates all the views and implements NavigationCall to allow controllers to access its methods.
 */
public class ViewManager implements NavigationCall {
    private final ViewFactory viewFactory;
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private IngredientRepository ingredientRepository;
    private final RecipeViewFactory recipeviewFactory;


    public ViewManager(ViewFactory viewFactory,
                       AnalyzeImageInteractor analyzeImageInteractor,
                       RecipeViewFactory recipeviewFactory) {
        this.viewFactory = viewFactory;
        this.analyzeImageInteractor = analyzeImageInteractor;
        this.recipeviewFactory = recipeviewFactory;
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
        RecipeListGUI recipeListView = recipeviewFactory.createRecipeListView();
        recipeListView.setVisible(true);
    }

    public void showRecipeDetailView(Recipe recipe, RecipeListGUI recipeListGUI) {
        RecipeDetailGUI recipeDetailView = recipeviewFactory.createRecipeDetailView(recipe, recipeListGUI);
        recipeDetailView.setVisible(true);
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
