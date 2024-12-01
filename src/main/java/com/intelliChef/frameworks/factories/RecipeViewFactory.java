/*
package com.intelliChef.frameworks.factories;

import com.intelliChef.adapters.presentation.*;
import com.intelliChef.frameworks.gui.RecipeListGUI;
import com.intelliChef.frameworks.gui.RecipeDetailGUI;
import com.intelliChef.use_case.ports.input.LoadRecipesUseCase;
import com.intelliChef.use_case.ports.input.SelectRecipeUseCase;
import com.intelliChef.use_case.ports.input.NavigationUseCase;
import com.intelliChef.use_case.dto.RecipeDTO;

*/
/**
 * Factory to create views and wire them with their dependencies.
 *//*

public class RecipeViewFactory {
    */
/**
     * Creates and initializes the recipe list view with its dependencies.
     *
     * @param loadRecipesUseCase use case for loading recipes
     * @param selectRecipeUseCase use case for selecting recipes
     * @return initialized recipe list view
     *//*

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

    */
/**
     * Creates and initializes the recipe detail view with its dependencies.
     *
     * @param navigationUseCase use case for navigation
     * @return initialized recipe detail view
     *//*

    public RecipeDetailView createRecipeDetailView(NavigationUseCase navigationUseCase) {
        // Create view and wire with navigation
        RecipeDetailGUI recipeDetailGUI = new RecipeDetailGUI(() ->
                navigationUseCase.navigateBack());

        // Create and wire presenter
        RecipeDetailPresenter recipeDetailPresenter = new RecipeDetailPresenter(recipeDetailGUI);

        return recipeDetailGUI;
    }

    */
/**
     * Converts a RecipeViewModel back to a RecipeDTO.
     * This is necessary because the view layer uses ViewModels but the use case layer expects DTOs.
     *
     * @param viewModel the view model to convert
     * @return the corresponding DTO
     *//*

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

    */
/**
     * Parses the formatted calories string back to a double.
     *//*

    private double parseCalories(String formattedCalories) {
        try {
            return Double.parseDouble(formattedCalories.split(" ")[0]);
        } catch (Exception e) {
            return 0.0;
        }
    }

    */
/**
     * Parses the formatted cooking time string back to minutes.
     *//*

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
}*/

package com.intelliChef.frameworks.factories;

import com.intelliChef.adapters.presentation.*;
import com.intelliChef.adapters.persistence.JSONRecipeRepository;
import com.intelliChef.frameworks.gui.RecipeListGUI;
import com.intelliChef.frameworks.gui.RecipeDetailGUI;
import com.intelliChef.use_case.LoadRecipesInteractor;
import com.intelliChef.use_case.SelectRecipeInteractor;
import com.intelliChef.use_case.NavigationInteractor;
import com.intelliChef.use_case.ports.output.RecipeRepository;
import com.intelliChef.use_case.dto.RecipeDTO;

/**
 * Factory responsible for creating and wiring all components related to recipe views.
 * This class encapsulates the complete object creation logic for the recipe feature.
 */
public class RecipeViewFactory {
    private final RecipeRepository repository;
    private RecipeListView recipeListView;
    private RecipeDetailView recipeDetailView;
    private LoadRecipesInteractor loadRecipesInteractor;
    private SelectRecipeInteractor selectRecipeInteractor;
    private NavigationInteractor navigationInteractor;

    public RecipeViewFactory() {
        this.repository = new JSONRecipeRepository();
    }

    /**
     * Creates and initializes a complete recipe list view with all its dependencies.
     *
     * @return configured recipe list view
     */
    public RecipeListView createRecipeListView() {
        // First create the list GUI since it will be needed by other components
        RecipeListGUI listGUI = new RecipeListGUI(null); // Temporary null listener
        this.recipeListView = listGUI;

        // Create the detail view with navigation
        RecipeDetailGUI detailGUI = new RecipeDetailGUI(() -> {
            navigationInteractor.navigateBack();
            listGUI.setVisible(true);
        });
        this.recipeDetailView = detailGUI;

        // Create and wire presenters
        RecipeDetailPresenter detailPresenter = new RecipeDetailPresenter(detailGUI);
        selectRecipeInteractor = new SelectRecipeInteractor(detailPresenter);

        // Now set the real selection listener
        listGUI.setSelectionListener(viewModel -> {
            selectRecipeInteractor.selectRecipe(toDTO(viewModel));
            detailGUI.setVisible(true);
            listGUI.setVisible(false);
        });

        // Create and wire list presenter and interactor
        RecipeListPresenter listPresenter = new RecipeListPresenter(listGUI);
        loadRecipesInteractor = new LoadRecipesInteractor(repository, listPresenter);

        // Create navigation interactor
        NavigationPresenter navigationPresenter = new NavigationPresenter(listGUI);
        navigationInteractor = new NavigationInteractor(navigationPresenter);

        // Load initial data
        loadRecipesInteractor.loadRecipes();

        return listGUI;
    }

    /**
     * Returns the current recipe detail view.
     * This should only be called after createRecipeListView().
     *
     * @return the recipe detail view
     */
    public RecipeDetailView getRecipeDetailView() {
        if (recipeDetailView == null) {
            throw new IllegalStateException("Recipe detail view not initialized. Call createRecipeListView first.");
        }
        return recipeDetailView;
    }

    /**
     * Returns the load recipes interactor.
     *
     * @return the load recipes interactor
     */
    public LoadRecipesInteractor getLoadRecipesInteractor() {
        return loadRecipesInteractor;
    }

    /**
     * Returns the select recipe interactor.
     *
     * @return the select recipe interactor
     */
    public SelectRecipeInteractor getSelectRecipeInteractor() {
        return selectRecipeInteractor;
    }

    /**
     * Returns the navigation interactor.
     *
     * @return the navigation interactor
     */
    public NavigationInteractor getNavigationInteractor() {
        return navigationInteractor;
    }

    /**
     * Converts a RecipeViewModel to a RecipeDTO.
     */
    private RecipeDTO toDTO(RecipeViewModel viewModel) {
        return new RecipeDTO(
                viewModel.getDisplayName(),
                viewModel.getImageUrl(),
                parseCalories(viewModel.getFormattedCalories()),
                parseCookingTime(viewModel.getFormattedTime()),
                viewModel.getUrl()
        );
    }

    private double parseCalories(String formattedCalories) {
        try {
            return Double.parseDouble(formattedCalories.split(" ")[0]);
        } catch (Exception e) {
            return 0.0;
        }
    }

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