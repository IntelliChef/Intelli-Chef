package interface_adapters.presentation;

/**
 * Interface for displaying recipe details.
 * This interface is in the interface adapters layer and will be
 * implemented by the UI framework in the outermost layer.
 */
public interface RecipeDetailView {
    /**
     * Displays the details of a recipe.
     *
     * @param recipe the recipe view model to display
     */
    void displayRecipeDetail(RecipeViewModel recipe);
}