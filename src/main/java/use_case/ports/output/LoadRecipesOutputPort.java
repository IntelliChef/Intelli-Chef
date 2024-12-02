package use_case.ports.output;

import use_case.dto.RecipeDTO;
import java.util.List;

/**
 * Output port for presenting loaded recipes.
 * This interface defines how the use case will present its results to the outer layers.
 * Following Clean Architecture, this interface is in the use cases layer and
 * will be implemented by presenters in the interface adapters layer.
 */
public interface LoadRecipesOutputPort {
    /**
     * Presents the loaded recipes to the user interface.
     *
     * @param recipes list of recipe data transfer objects to be presented
     */
    void presentRecipes(List<RecipeDTO> recipes);

    /**
     * Presents an error message when recipe loading fails.
     *
     * @param error the error message to be presented
     */
    void presentError(String error);
}
