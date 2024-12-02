package interface_adapters.presentation;

import java.util.List;

/**
 * Interface for displaying the recipe list.
 * This interface is in the interface adapters layer and will be
 * implemented by the UI framework in the outermost layer.
 */
public interface RecipeListView {
    /**
     * Displays a list of recipes.
     *
     * @param recipes the list of recipe view models to display
     */
    void displayRecipes(List<RecipeViewModel> recipes);

    /**
     * Displays an error message.
     *
     * @param error the error message to display
     */
    void displayError(String error);
}
