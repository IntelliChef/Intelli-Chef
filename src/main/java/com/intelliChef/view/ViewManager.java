package com.intelliChef.view;

import com.intelliChef.app.IngredientListFactory;
import com.intelliChef.entities.Ingredient;
import com.intelliChef.use_case.IngredientRepository;
import com.intelliChef.use_case.analyzeImage.AnalyzeImageInteractor;
import com.intelliChef.adapters.presentation.RecipeListView;
import com.intelliChef.adapters.presentation.RecipeDetailView;
import com.intelliChef.use_case.ports.input.LoadRecipesUseCase;
import com.intelliChef.use_case.ports.input.SelectRecipeUseCase;
import com.intelliChef.use_case.ports.input.NavigationUseCase;
import com.intelliChef.frameworks.factories.RecipeViewFactory;

import java.util.List;

public class ViewManager implements NavigationCall {
    private final ViewFactory viewFactory;
    private final AnalyzeImageInteractor analyzeImageInteractor;
    private IngredientRepository ingredientRepository;
    private final RecipeViewFactory recipeViewFactory;
    private final LoadRecipesUseCase loadRecipesUseCase;
    private final SelectRecipeUseCase selectRecipeUseCase;
    private final NavigationUseCase navigationUseCase;
    private RecipeListView currentRecipeListView;
    private RecipeDetailView currentRecipeDetailView;

    public ViewManager(
            ViewFactory viewFactory,
            AnalyzeImageInteractor analyzeImageInteractor,
            RecipeViewFactory recipeViewFactory,
            LoadRecipesUseCase loadRecipesUseCase,
            SelectRecipeUseCase selectRecipeUseCase,
            NavigationUseCase navigationUseCase) {
        this.viewFactory = viewFactory;
        this.analyzeImageInteractor = analyzeImageInteractor;
        this.recipeViewFactory = recipeViewFactory;
        this.loadRecipesUseCase = loadRecipesUseCase;
        this.selectRecipeUseCase = selectRecipeUseCase;
        this.navigationUseCase = navigationUseCase;
    }

    public void showRecipeUploadView() {
        RecipeUploadView recipeUploadView = viewFactory.createRecipeUploadView(analyzeImageInteractor);
        recipeUploadView.setVisible(true);
    }

    public void showIngredientsDetectedView(List<Ingredient> ingredientList) {
        IngredientsDetectedView ingredientsDetectedView = viewFactory.createIngredientsDetectedView(ingredientList);
        ingredientsDetectedView.setVisible(true);
    }

    public void showIngredientListView(IngredientRepository ingredientRepository) {
        IngredientListView ingredientListView = IngredientListFactory.create(ingredientRepository, this);
        ingredientListView.setVisible(true);
    }

    public void showRecipeListView() {
        currentRecipeListView = recipeViewFactory.createRecipeListView(
                loadRecipesUseCase,
                selectRecipeUseCase
        );
        ((javax.swing.JFrame) currentRecipeListView).setVisible(true);
    }

    public void showRecipeDetailView() {
        currentRecipeDetailView = recipeViewFactory.createRecipeDetailView(navigationUseCase);
        ((javax.swing.JFrame) currentRecipeDetailView).setVisible(true);
        if (currentRecipeListView instanceof javax.swing.JFrame) {
            ((javax.swing.JFrame) currentRecipeListView).setVisible(false);
        }
    }

    @Override
    public void navigateToRecipeUploadView() {
        showRecipeUploadView();
    }

    @Override
    public void navigateToIngredientsDetectedView(List<Ingredient> ingredientList) {
        showIngredientsDetectedView(ingredientList);
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