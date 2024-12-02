package interface_adapters.presentation;

import use_case.dto.RecipeDTO;
import use_case.ports.output.SelectRecipeOutputPort;

/**
 * Presenter for the recipe details.
 * This class implements the output port from the use case layer and
 * transforms the data into a format suitable for the detail view.
 */
public class RecipeDetailPresenter implements SelectRecipeOutputPort {
    private final RecipeDetailView view;

    /**
     * Constructs a new RecipeDetailPresenter.
     *
     * @param view the view that will display the recipe details
     */
    public RecipeDetailPresenter(RecipeDetailView view) {
        this.view = view;
    }

    /**
     * Transforms and presents the recipe details to the view.
     *
     * @param recipe the recipe DTO to be presented
     */
    @Override
    public void presentRecipeDetails(RecipeDTO recipe) {
        RecipeViewModel viewModel = toViewModel(recipe);
        view.displayRecipeDetail(viewModel);
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