package com.intelliChef.adapters.presentation;

import com.intelliChef.use_case.dto.RecipeDTO;
import com.intelliChef.use_case.ports.output.LoadRecipesOutputPort;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Presenter for the recipe list.
 * This class implements the output port from the use case layer and
 * transforms the data into a format suitable for the view.
 */
public class RecipeListPresenter implements LoadRecipesOutputPort {
    private final RecipeListView view;

    /**
     * Constructs a new RecipeListPresenter.
     *
     * @param view the view that will display the recipes
     */
    public RecipeListPresenter(RecipeListView view) {
        this.view = view;
    }

    /**
     * Transforms and presents the recipes to the view.
     *
     * @param recipes list of recipe DTOs to be presented
     */
    @Override
    public void presentRecipes(List<RecipeDTO> recipes) {
        List<RecipeViewModel> viewModels = recipes.stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
        view.displayRecipes(viewModels);
    }

    /**
     * Presents an error message to the view.
     *
     * @param error the error message to be presented
     */
    @Override
    public void presentError(String error) {
        view.displayError(error);
    }

    /**
     * Converts a RecipeDTO to a RecipeViewModel.
     *
     * @param recipe the DTO to convert
     * @return the corresponding view model
     */
    private RecipeViewModel toViewModel(RecipeDTO recipe) {
        return new RecipeViewModel(
                recipe.getName(),
                recipe.getImageUrl(),
                formatCalories(recipe.getCalories()),
                formatCookingTime(recipe.getCookingTime()),
                recipe.getUrl()
        );
    }

    /**
     * Formats the calories for display.
     *
     * @param calories the calorie count to format
     * @return formatted calorie string
     */
    private String formatCalories(double calories) {
        return String.format("%.0f calories", calories);
    }

    /**
     * Formats the cooking time for display.
     *
     * @param minutes the cooking time in minutes
     * @return formatted time string
     */
    private String formatCookingTime(int minutes) {
        return minutes > 0 ? minutes + " mins" : "Time not available";
    }
}