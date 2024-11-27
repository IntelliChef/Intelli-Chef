package com.intelliChef.frameworks.factories;

import com.intelliChef.adapters.presentation.*;
import com.intelliChef.frameworks.gui.RecipeListGUI;
import com.intelliChef.frameworks.gui.RecipeDetailGUI;
import com.intelliChef.use_case.ports.input.LoadRecipesUseCase;
import com.intelliChef.use_case.ports.input.SelectRecipeUseCase;
import com.intelliChef.use_case.ports.input.NavigationUseCase;
import com.intelliChef.use_case.dto.RecipeDTO;

/**
 * Factory to create views and wire them with their dependencies.
 */
public class RecipeViewFactory {
    /**
     * Creates and initializes the recipe list view with its dependencies.
     *
     * @param loadRecipesUseCase use case for loading recipes
     * @param selectRecipeUseCase use case for selecting recipes
     * @return initialized recipe list view
     */
    public RecipeListView createRecipeListView(
            LoadRecipesUseCase loadRecipesUseCase,
            SelectRecipeUseCase selectRecipeUseCase) {

        // Create presenters
        RecipeListGUI recipeListGUI = new RecipeListGUI(viewModel ->
                selectRecipeUseCase.selectRecipe(toDTO(viewModel)));

        RecipeListPresenter recipeListPresenter = new RecipeListPresenter(recipeListGUI);

        // Load initial data
        loadRecipesUseCase.loadRecipes();

        return recipeListGUI;
    }

    /**
     * Creates and initializes the recipe detail view with its dependencies.
     *
     * @param navigationUseCase use case for navigation
     * @return initialized recipe detail view
     */
    public RecipeDetailView createRecipeDetailView(NavigationUseCase navigationUseCase) {
        // Create view and wire with navigation
        RecipeDetailGUI recipeDetailGUI = new RecipeDetailGUI(() ->
                navigationUseCase.navigateBack());

        // Create and wire presenter
        RecipeDetailPresenter recipeDetailPresenter = new RecipeDetailPresenter(recipeDetailGUI);

        return recipeDetailGUI;
    }

    /**
     * Converts a RecipeViewModel back to a RecipeDTO.
     * This is necessary because the view layer uses ViewModels but the use case layer expects DTOs.
     *
     * @param viewModel the view model to convert
     * @return the corresponding DTO
     */
    private RecipeDTO toDTO(RecipeViewModel viewModel) {
        // Parse the formatted values back to their original types
        double calories = parseCalories(viewModel.getFormattedCalories());
        int cookingTime = parseCookingTime(viewModel.getFormattedTime());

        return new RecipeDTO(
                viewModel.getDisplayName(),
                viewModel.getImageUrl(),
                calories,
                cookingTime,
                viewModel.getUrl()
        );
    }

    /**
     * Parses the formatted calories string back to a double.
     */
    private double parseCalories(String formattedCalories) {
        try {
            return Double.parseDouble(formattedCalories.split(" ")[0]);
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * Parses the formatted cooking time string back to minutes.
     */
    private int parseCookingTime(String formattedTime) {
        if (formattedTime.equals("Time not available")) {
            return -1;
        }
        try {
            return Integer.parseInt(formattedTime.split(" ")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
}